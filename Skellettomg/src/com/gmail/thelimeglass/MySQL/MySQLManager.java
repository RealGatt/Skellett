package com.gmail.thelimeglass.MySQL;

import com.gmail.thelimeglass.Skellett;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import java.sql.*;

public class MySQLManager implements Listener {
    private static Connection con;

    public static Connection getConnection() {
        return con;
    }

    public static void setConnection(String host, String user, String password, String database) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + Skellett.getInstance().getMysqlData().getInt("MySQLSetup.Port", 3306) + "/" + database, user, password);
            if (Bukkit.getServer().getPluginManager().getPlugin("Skellett").getConfig().getBoolean("debug")) {
                Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix() + "&aMySQL connected! Host: " + host + " user: " + user + " password: " + password.replaceAll("", "*") + " user: " + user));
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix() + "&cMySQL connect error: " + e.getMessage()));
        }
    }

    public static void connect() {
        String host = Skellett.getInstance().getMysqlData().getString("MySQLSetup.Host", "");
        String user = Skellett.getInstance().getMysqlData().getString("MySQLSetup.Username", "");
        String password = Skellett.getInstance().getMysqlData().getString("MySQLSetup.Password", "");
        String database = Skellett.getInstance().getMysqlData().getString("MySQLSetup.Database", "");
        if (host.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix() + "&cMySQL error: Host is blank"));
        } else if (user.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix()  + "&cMySQL error: Username is blank"));
        } else if (password.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix()+ "&cMySQL error: Password is blank"));
        } else if (database.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix()+ "&cMySQL error: Database is blank"));
        } else if (getConnection() == null) {
            setConnection(host, user, password, database);
        }
    }

    public static void disconnect() {
        try {
            if (getConnection() != null) {
                con.close();
                if (Bukkit.getServer().getPluginManager().getPlugin("Skellett").getConfig().getBoolean("debug")) {
                    Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix() + "&cMySQL disconnected."));
                }
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(Skellett.cc(Skellett.getPrefix() + "&cMySQL disconnect error: " + e.getMessage()));
        }
    }

    public static void update(String command) {
        if (command == null) {
            return;
        }
        connect();
        try {
            Statement st = getConnection().createStatement();
            st.executeUpdate(command);
            st.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage((Object) ChatColor.RED + "MySQL Update:");
            Bukkit.getConsoleSender().sendMessage((Object) ChatColor.RED + "Command: " + command);
            Bukkit.getConsoleSender().sendMessage((Object) ChatColor.RED + "Error: " + e.getMessage());
        }
    }

    public static ResultSet query(String command) {
        if (command == null) {
            return null;
        }
        connect();
        ResultSet rs = null;
        try {
            Statement st = getConnection().createStatement();
            rs = st.executeQuery(command);
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage((Object) ChatColor.RED + "MySQL Query:");
            Bukkit.getConsoleSender().sendMessage((Object) ChatColor.RED + "Command: " + command);
            Bukkit.getConsoleSender().sendMessage((Object) ChatColor.RED + "Error: " + e.getMessage());
        }
        return rs;
    }
}
