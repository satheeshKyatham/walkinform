package com.godrej.properties.serviceimpl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.constants.KeyConstants;
import com.godrej.properties.dto.LdapUserDetailsDto;
import com.godrej.properties.dto.SysConfigEnum;
import com.godrej.properties.master.service.SysConfigService;
import com.godrej.properties.service.LdapService;

@Service("ldapService")
@Transactional
public class LdapServiceImpl implements LdapService {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Override
	public LdapUserDetailsDto getldapUserData(LdapUserDetailsDto dto) {
		LdapUserDetailsDto ldapDto = new LdapUserDetailsDto();
		String auth_method = "simple";
		String ldap_version = "3";
		String ldap_host = sysConfigService.getValue(SysConfigEnum.AD_IP, "AD_IP");
		//String ldap_host = KeyConstants.LDAP_HOST;// 10.1.15.227//10.1.2.13
		//String ldap_host = adIP;
		String ldap_port = KeyConstants.LDAP_PORT;
		String ldap_dn = KeyConstants.LDAP_DN;
		String ldap_pw = KeyConstants.LDAP_PW;// A sdf@123
		String base_dn = "DC=godrejinds,DC=com";
		log.info("Enter Auth........");
		String emailId = "", username = "";
		LdapContext ctx = null;
		// DirContext ctx = null;
		Hashtable env = new Hashtable();
		// Here we store the returned LDAP object data
		String dn = "";
		// This will hold the returned attribute list
		Attributes attrs;
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + ldap_host + ":" + ldap_port);
		env.put(Context.SECURITY_AUTHENTICATION, auth_method);
		env.put(Context.SECURITY_PRINCIPAL, ldap_dn);
		env.put(Context.SECURITY_CREDENTIALS, ldap_pw);
		env.put("java.naming.ldap.version", ldap_version);
		env.put(Context.REFERRAL, "ignore");
		try {
			log.info("Connecting to host " + ldap_host + " at port " + ldap_port + "...");
			ctx = new InitialLdapContext(env, null);
			log.info("DN LDAP authentication successful!");
			// Specify the attribute list to be returned
			// String[] attrIDs = { "memberOf" };
			String[] attrIDs = { "distinguishedName", "sn", "givenname", "mail", "telephonenumber", "sAMAccountName",
					"postalCode", "cn", "accountExpires" ,"initials","description","physicalDeliveryOfficeName",
					"postOfficeBox","l","c","company","manager","title","managedBy","memberOf",
					"department","targetAddress","userAccountControl","mobile" };
			SearchControls ctls = new SearchControls();
			log.info("attrIDs:-",attrIDs);
			ctls.setReturningAttributes(attrIDs);
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			/*
			 * String sAMAccountName = ldap_dn.replaceAll("@godrejinds.com",
			 * ""); System.out.println("sAMAccountName:-"+sAMAccountName);
			 */
			/*
			 * String filter =
			 * "(&(objectClass=user)(mail=ashlesha.munshi@godrejproperties.com))";
			 */
			String filter = "(&(objectClass=user)(mail=" + dto.getEmail() + "))";
			// Search the subtree for objects using the given filter
			NamingEnumeration answer = ctx.search(base_dn, filter, ctls);
			// Print the answer
			// Search.printSearchEnumeration(answer);
			log.info("MoreElements:-" + answer.hasMoreElements());
			if (answer.hasMore()) {
				Properties authEnv = new Properties();
				authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				authEnv.put(Context.PROVIDER_URL, "ldap://" + ldap_host + ":" + ldap_port);
				authEnv.put(Context.SECURITY_PRINCIPAL, dto.getEmail());
				authEnv.put(Context.SECURITY_CREDENTIALS, dto.getPassword());    //Gpl@2020
				try
				{
					new InitialDirContext(authEnv);
				}
				catch (Exception e) {
					log.error("LDAP Employee connection failed",e);
					ldapDto.setIsvalid(false);
					return ldapDto;
				}
				SearchResult sr = (SearchResult) answer.next();
				log.info("sr.getName():-" + sr.getName());
				dn = sr.getName();
				attrs = sr.getAttributes();
				log.info("Found Object: " + dn + "," + base_dn);
				if (attrs != null) {
					// we have some attributes for this object
					NamingEnumeration ae = attrs.getAll();
					while (ae.hasMoreElements()) {
						Attribute attr = (Attribute) ae.next();
						String attrId = attr.getID();
						//log.info("Found Attribute: ", attrId);
						Enumeration vals = attr.getAll();
						while (vals.hasMoreElements()) {
							String attr_val = (String) vals.nextElement();
							//log.info(attrId + ": " + attr_val);
							if (attrId.equals("sn")) {
								ldapDto.setLastName(attr_val);
							}
							if (attrId.equals("mail")) {
								ldapDto.setEmail(attr_val);
							}
							if (attrId.equals("givenName")) {
								ldapDto.setFirstName(attr_val);
							}
							if (attrId.equals("accountExpires")) {
								ldapDto.setAccountExpires(attr_val);
							}
							if (attrId.equals("distinguishedName")) {
								ldapDto.setDistinguishedName(attr_val);
							}
							if (attrId.equals("cn")) {
								ldapDto.setUserName(attr_val);
							}
							if (attrId.equals("sAMAccountName")) {
								ldapDto.setAccountName(attr_val);
							}
							if (attrId.equals("telephonenumber")) {
								ldapDto.setTelephonenumber(attr_val);
							}
							if (attrId.equals("mobile")) {
								ldapDto.setMobile(attr_val);
							}
							if (attrId.equals("postalCode")) {
								ldapDto.setPostalCode(attr_val);
							}
							if (attrId.equals("company")) {
								ldapDto.setCompany(attr_val);
							}
							if (attrId.equals("department")) {
								ldapDto.setDepartment(attr_val);
							}
							if (attrId.equals("memberOf")) {
								ldapDto.setGroups(attr_val);
							}
							if (attrId.equals("physicalDeliveryOfficeName")) {
								ldapDto.setOfficeName(attr_val);
							}
							if (attrId.equals("l")) {
								ldapDto.setCity(attr_val);
							}
							if (attrId.equals("targetAddress")) {
								ldapDto.setTargetAddress(attr_val);
							}
							if (attrId.equals("description")) {
								ldapDto.setDescription(attr_val);
							}
							if (attrId.equals("managedBy")) {
								ldapDto.setManagedBy(attr_val);
							}
							if (attrId.equals("title")) {
								ldapDto.setTitle(attr_val);
							}
							if (attrId.equals("postOfficeBox")) {
								ldapDto.setPostOffice(attr_val);
							}
							if (attrId.equals("initials")) {
								ldapDto.setMiddleName(attr_val);
							}							
						}
					}
					log.info("Email: " + attrs.get("mail").toString().replaceAll("mail: ", ""));
					/*
					 * emailId =
					 * attrs.get("mail").toString().replaceAll("mail: ", "");
					 */
					/*
					 * username = attrs.get("cn").toString().replaceAll("cn: ",
					 * "");
					 */
					dto.setEmail(attrs.get("mail").toString().replaceAll("mail: ", ""));
					dto.setUserName(attrs.get("cn").toString().replaceAll("cn: ", ""));
					log.info("accountExpires:-" + attrs.get("accountExpires").toString());
					ldapDto.setIsvalid(true);
				}
			}
			// Close the context when we're done
			ctx.close();
			return ldapDto;
		} catch (NamingException namEx) {
			log.info("LDAP connection failed!",namEx);
			 namEx.printStackTrace();
			ldapDto.setIsvalid(false);
			return ldapDto;
		}
	}
}
