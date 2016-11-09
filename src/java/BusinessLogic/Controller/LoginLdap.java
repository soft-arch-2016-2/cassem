/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 *
 * @author arqsoft_2016_2
 */
public class LoginLdap {

    private LDAPConnection lc = new LDAPConnection();

    public String login(String user, String password) {

        if (connect()) {
            if (validatePassword(user, password)) {
                return "Inicio de sesión satisfactorio.";
            } else {
                return "Usuario y/o contraseña incorrectos.";
            }
        } else {
            return "Conexión al servidor LDAP fallida.";
        }
    }

    public Boolean connect() {

        String ldapHost = "192.168.2.10";
        String dn = "cn=admin,dc=arqsoft,dc=unal,dc=edu,dc=co";
        String password = "arqsoft20162";

        int ldapPort = LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;

        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("==== Conectado al servidor LDAP ====");
            lc.bind(ldapVersion, dn, password.getBytes("UTF8"));
            System.out.println("==== Autenticado en el servidor ====");
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            System.out.println("==== ERROR al conectarse al servidor LDAP ====");
            return false;
        }

    }

    public Boolean validatePassword(String user, String password) {

        String dn = "cn=" + user + ",ou=Cassem,dc=arqsoft,dc=unal,dc=edu,dc=co";

        try {
            lc.bind(dn, password);
            System.out.println("==== Contraseña validada ====");
            return true;
        } catch (LDAPException ex) {
            System.out.println("==== ERROR al validar la contraseña ====");
            return false;
        }
    }

    public Boolean createUser(String username, String userpassword) {

        try {

            String ldapHost = "ldap://192.168.2.10:" + LDAPConnection.DEFAULT_PORT;
            String adminDN = "cn=admin,dc=arqsoft,dc=unal,dc=edu,dc=co";
            String password = "arqsoft20162";

            Hashtable<String, String> environment = new Hashtable<>();
            environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            environment.put(Context.PROVIDER_URL, ldapHost); // Revisar
            environment.put(Context.SECURITY_AUTHENTICATION, "simple");
            environment.put(Context.SECURITY_PRINCIPAL, adminDN);
            environment.put(Context.SECURITY_CREDENTIALS, password);

            DirContext dirContext = new InitialDirContext(environment);
            Attribute userCn = new BasicAttribute("cn", username);
            Attribute userSn = new BasicAttribute("sn", username);
            Attribute userMail = new BasicAttribute("mail", username + "@architecture.unal.edu.co");
            Attribute userPhone = new BasicAttribute("Mobile", "123");
            Attribute userGivename = new BasicAttribute("givenname", username);
            Attribute userTitle = new BasicAttribute("PersonalTitle", "user for test");
            Attribute userUserPassword = new BasicAttribute("userPassword", userpassword);
            Attribute userstatus = new BasicAttribute("userstatus", "A");

            Attribute objectClass = new BasicAttribute("objectclass");
            objectClass.add("top");
            objectClass.add("person");
            objectClass.add("organizationalPerson");
            objectClass.add("inetOrgPerson");
            

            Attributes entry = new BasicAttributes();
            entry.put(userCn);
            entry.put(userSn);
            entry.put(userMail);
            entry.put(userPhone);
            entry.put(userGivename);
            entry.put(userTitle);
            entry.put(userUserPassword);
            entry.put(objectClass);
            entry.put(userstatus);

            String entryDN = "ou=Cassem,"+"cn=" + username +",dc=arqsoft,dc=unal,dc=edu,dc=co";
            dirContext.createSubcontext(entryDN, entry);
            
        } catch (NamingException ex) {
            Logger.getLogger(LoginLdap.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
        return true;

    }
}
