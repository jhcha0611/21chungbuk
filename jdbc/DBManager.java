package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {

   Connection cn;
   Statement st;
   String path = System.getProperty("user.dir").replace("\\", "/") + "/지급자료/";

   public DBManager() {
      try {

         Class.forName("com.mysql.cj.jdbc.Driver");
         cn = DriverManager.getConnection("jdbc:mysql://localhost/?allowLoadLocalInfile=true&serverTimezone=UTC",
               "root", "1234");
         st = cn.createStatement();

         st.executeUpdate("SET GLOBAL local_infile = true");
         st.executeUpdate("DROP DATABASE IF EXISTS `충북21`");
         st.executeUpdate("CREATE DATABASE `충북21`");
         st.executeUpdate("USE `충북21`");

         st.executeUpdate("DROP USER IF EXISTS `user`");
         st.executeUpdate("CREATE USER `user` IDENTIFIED BY '1234'");
         st.executeUpdate("GRANT INSERT, SELECT, DELETE, UPDATE ON `충북21`.* TO `user`");

         st.executeUpdate("CREATE TABLE `Region`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, name varchar(10))");
         st.executeUpdate(
               "CREATE TABLE `category`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, name varchar(10))");
         st.executeUpdate(
               "CREATE TABLE `artist`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, name varchar(30), about text)");
         st.executeUpdate(
               "CREATE TABLE `album`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, name varchar(50), artist int, category int, `release` date, FOREIGN KEY(artist) REFERENCES artist(serial), FOREIGN KEY(category) REFERENCES category(serial))");
         st.executeUpdate(
               "CREATE TABLE `song`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, name varchar(50), `length` time, `album` int, titlesong int, FOREIGN KEY(album) REFERENCES `album`(serial))");
         st.executeUpdate(
               "CREATE TABLE `user`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, id varchar(10), pw varchar(10), name varchar(30), email varchar(50), region int, birth date, FOREIGN KEY(region) REFERENCES region(serial))");
         st.executeUpdate(
               "CREATE TABLE `playlist`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, name varchar(50), user int, FOREIGN KEY(user) REFERENCES user(serial))");
         st.executeUpdate(
               "CREATE TABLE `songlist`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, playlist int, song int, FOREIGN KEY(playlist) REFERENCES playlist(serial), FOREIGN KEY(song) REFERENCES song(serial))");
         st.executeUpdate(
               "CREATE TABLE `history`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, song int, user int, date date, FOREIGN KEY(song) REFERENCES song(serial), FOREIGN KEY(user) REFERENCES user(serial))");
         st.executeUpdate(
               "CREATE TABLE `favorite`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, user int, song int, FOREIGN KEY(user) REFERENCES user(serial), FOREIGN KEY(song) REFERENCES song(serial))");
         st.executeUpdate(
               "CREATE TABLE `community`(serial int AUTO_INCREMENT PRIMARY KEY NOT NULL, user int, artist int, `rate` int, `content` text, `date` date, FOREIGN KEY(user) REFERENCES user(serial), FOREIGN KEY(artist) REFERENCES artist(serial))");

         String tables[] = { "region", "category", "artist", "album", "song", "user", "playlist", "songlist",
               "history", "favorite", "community" };
         for (String table : tables) {
            st.executeUpdate("LOAD DATA LOCAL INFILE '" + path + table + ".txt' INTO TABLE `" + table
                  + "` FIELDS TERMINATED BY '\t' IGNORE 1 LINES");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      new DBManager();
   }
}