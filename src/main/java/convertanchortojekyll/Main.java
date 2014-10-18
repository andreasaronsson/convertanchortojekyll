package convertanchortojekyll;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String query = "SELECT id,title,slug,created,html from anchor_posts";
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/anchor", "anchor", "anchor");
                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String slug = rs.getString("slug");
                String date = rs.getDate("created").toString();
                String html = rs.getString("html");
                String title = rs.getString("title");
                putFile(new Post(id, title, slug, html, date));
            }
        }
    }

    private static void putFile(Post post) throws IOException {
        if (!post.slug.startsWith("-")) {
            post.slug = "-" + post.slug;
        }
        Path path = Paths.get(post.date + post.slug + ".md");
        String header = "---\nlayout: post\ntitle: \"" + post.title + "\"\n---\n\n";
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bw.write(header);
            bw.write(post.html);
            bw.close();
        }
    }
}