package com.tomcat.hosting.utils;

import java.util.HashMap;
import java.util.Map;


public class MenuTree {
	public static final String ACCOUNTINFO = "account-info";
	public static final String DATABASEUTILS = "database-utils";
	public static final String EMAILUTILS = "email-utils";
	public static final String SERVERUTILS = "server-utils";
	public static final String TOMCATUTILS = "tomcat-utils";
	public static final String VIRTUALHOST = "virtual-host";
	
	public static final int DEDCIATED = 0;
	public static final int HOSTING = 1;
	MenuTreeItem root;
	public MenuTree(String name, MenuTreeItem[] items) {
		root = new MenuTreeItem(name, null, null, null, items);
	}
	public MenuTreeItem getRoot() {
		return root;
	}
	public static void reset() {
		for (int i = 0; i < hostingMenuItems.length; i++) {
			hostingMenuItems[i].setClassValue("tab");
		}
		for (int i = 0; i < subMenuTomcat.length; i++) {
			subMenuTomcat[i].setClassValue("inst3");
		}
		for (int i = 0; i < subMenuMail.length; i++) {
			subMenuMail[i].setClassValue("inst3");
		}
		for (int i = 0; i < subMenuVirtual.length; i++) {
			subMenuVirtual[i].setClassValue("inst3");
		}
		for (int i = 0; i < subMenuAccountInfo.length; i++) {
			subMenuAccountInfo[i].setClassValue("inst3");
		}
		for (int i = 0; i < subMenuServers.length; i++) {
			subMenuServers[i].setClassValue("inst3");
		}
		for (int i = 0; i < subMenuDatabases.length; i++) {
			subMenuDatabases[i].setClassValue("inst3");
		}
	}
	public static MenuTree getMenu(int value) {
		if (HOSTING == value) return hostingmenu;
		else return dedicatedservermenu;
	}
	
	public static class MenuTreeItem {
		String name;
		String href;
		String classValue;
		String value;
		MenuTreeItem[] children;
		public MenuTreeItem(String n, String href, 
				String classValue, String value, MenuTreeItem[] children) {
			this.name = n;
			this.href = href;
			this.classValue = classValue;
			this.value = value;
			this.children = new MenuTreeItem[children.length];
			for (int i = 0; i < children.length; i++) {
				this.children[i] = children[i];
			}
		}
		public void setClassValue(String newvalue) {
			classValue = newvalue;
		}
		
		public MenuTreeItem[] getChildren() {
			return children;
		}
		public MenuTreeItem findSubMenu(String name) {
			for (int i = 0; i < children.length; i++) {
				if (null != name && name.equals(children[i].name)) return children[i];
			}
			return null;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getHref() {
			return href;
		}
		public void setHref(String href) {
			this.href = href;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getClassValue() {
			return classValue;
		}
		public void setChildren(MenuTreeItem[] children) {
			this.children = children;
		}
	}
	private static final String lastIp = "223"; //NEED to revisit
//	= account.getInternalIpAddress()
//	.substring(account.getInternalIpAddress()
//			.lastIndexOf(".")+1);
	private static final String mysqlUrl = 
			new StringBuilder().append("http://mysql")
			.append(lastIp)
			.append(".tomcathostingservice.com").toString();
	
	private static final MenuTreeItem[] subMenuTomcat = 
	{
		new MenuTreeItem("tomcatinfo", "account/tomcat-info.xhtml",  "inst3", "Tomcat Info", new MenuTreeItem[0]),
		new MenuTreeItem("checktomcat", "checktomcat.asp",  "inst3", "Check Tomcat Status", new MenuTreeItem[0]),
		new MenuTreeItem("restarttomcat", "runtomcat.asp?command=restart", "inst3", "Restart Tomcat", new MenuTreeItem[0]),
		new MenuTreeItem("tomcatutils", "account/tomcat-utils.xhtml",  "inst3", "Help", new MenuTreeItem[0]),
	};
	
	private static final MenuTreeItem[] subMenuMail = 
	{
		new MenuTreeItem("createemail", "account/create-email.xhtml",  "inst3", "Create An Email", new MenuTreeItem[0]),
		new MenuTreeItem("viewemails", "account/view-emails.xhtml",  "inst3", "View Emails", new MenuTreeItem[0]),
		new MenuTreeItem("webMail", "http://mail.tomcathostingservice.com:8080/intouch2", "inst3", "Web Mail", new MenuTreeItem[0]),
	};
	
	private static final MenuTreeItem[] subMenuVirtual = 
	{
		new MenuTreeItem("createvirtualhost", "account/create-virtual-host.xhtml",  "inst3", "Create Virtual Hosting", new MenuTreeItem[0]),
		new MenuTreeItem("viewdomains", "account/view-domains.xhtml",  "inst3", "View Domains", new MenuTreeItem[0]),
		new MenuTreeItem("hostingutils", "account/hosting-utils.xhtml", "inst3", "Help", new MenuTreeItem[0]),
	};
	
	private static final MenuTreeItem[] subMenuAccountInfo = 
	{
		new MenuTreeItem("accountinfo", "account/account-info.xhtml",  "inst3", "Account Info", new MenuTreeItem[0]),
		new MenuTreeItem("billinghistory", "account/billing-history.xhtml",  "inst3", "Billing History", new MenuTreeItem[0]),
		new MenuTreeItem("changepassword", "account/change-password.xhtml", "inst3", "Change Password", new MenuTreeItem[0]),
		new MenuTreeItem("makepayment", "make-payment.asp",  "inst3", "Make Payment", new MenuTreeItem[0]),
	};
	
	private static final MenuTreeItem[] subMenuServers = 
	{
		//new MenuTreeItem("addIp", "account/command.xhtml?cmd=addIp",  "inst3", "Add IP to white-list", new MenuTreeItem[0]),
		new MenuTreeItem("filemanager", "account/file-manager.xhtml",  "inst3", "File Manager", new MenuTreeItem[0]),
		new MenuTreeItem("serverutils", "account/server-utils.xhtml", "inst3", "Help", new MenuTreeItem[0]),
	};
	
	private static final MenuTreeItem[] subMenuDatabases = 
	{
		new MenuTreeItem("createdatabase", "account/create-database.xhtml",  "inst3", "Create Database", new MenuTreeItem[0]),
		new MenuTreeItem("viewdatabases", "account/view-databases.xhtml",  "inst3", "View Database", new MenuTreeItem[0]),
		new MenuTreeItem("mysqlAdmin", "mysqlAdmin", "inst3", "MySQL Admin", new MenuTreeItem[0]),
		new MenuTreeItem("databaseutils", "account/database-utils.xhtml",  "inst3", "Help", new MenuTreeItem[0]),
	};
	
	private static final MenuTreeItem[] hostingMenuItems = 
	{
		new MenuTreeItem(ACCOUNTINFO, "account/account-info.xhtml",  "tab", "Account Info", subMenuAccountInfo),
		new MenuTreeItem(DATABASEUTILS, "account/create-database.xhtml",  "tab", "Database Utils", subMenuDatabases),
		new MenuTreeItem(EMAILUTILS, "account/create-email.xhtml",  "tab", "Email Utils", subMenuMail),
		new MenuTreeItem(SERVERUTILS, "account/server-utils.xhtml",  "tab", "Server Utils", subMenuServers),
		new MenuTreeItem(TOMCATUTILS, "account/tomcat-info.xhtml",  "tab", "Tomcat Utils", subMenuTomcat),
		new MenuTreeItem(VIRTUALHOST, "account/create-virtual-host.xhtml",  "tab", "Virtual Host", subMenuVirtual),
	};
	private static final MenuTreeItem[] dedicatedMenuItems = 
	{
		new MenuTreeItem("email-utils", "account/create-email.xhtml",  "tab", "Email Utils", subMenuMail),
		new MenuTreeItem("account-info", "account/account-info.xhtml",  "tab", "Account Info", subMenuAccountInfo),
	};
	
	static final MenuTree hostingmenu = new MenuTree("hosting", hostingMenuItems);
	static final MenuTree dedicatedservermenu = new MenuTree("dedicated", dedicatedMenuItems);
	
	
	public static Map<String, String> MENUMAP = new HashMap<String, String>();
	static {
		for (int i = 0; i < subMenuTomcat.length; i++) {
			MENUMAP.put(subMenuTomcat[i].name, TOMCATUTILS);
		}
		for (int i = 0; i < subMenuMail.length; i++) {
			MENUMAP.put(subMenuMail[i].name, EMAILUTILS);
		}
		for (int i = 0; i < subMenuVirtual.length; i++) {
			MENUMAP.put(subMenuVirtual[i].name, VIRTUALHOST);
		}
		for (int i = 0; i < subMenuAccountInfo.length; i++) {
			MENUMAP.put(subMenuAccountInfo[i].name, ACCOUNTINFO);
		}
		for (int i = 0; i < subMenuServers.length; i++) {
			MENUMAP.put(subMenuServers[i].name, SERVERUTILS);
		}
		for (int i = 0; i < subMenuDatabases.length; i++) {
			MENUMAP.put(subMenuDatabases[i].name, DATABASEUTILS);
		}
		
	}
}




