package convertanchortojekyll;

class Post {
    int id;
    String title;
    String slug;
    String html;
    String date;

    Post(int id, String title, String slug, String html, String date) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.html = html;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", slug=" + slug + ", html=" + html + ", date=" + date + "]";
    }

}
