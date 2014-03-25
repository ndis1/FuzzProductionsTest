package row;

public class JsonLstElement {
    private final String id;
    private final String type;
    private final String data;

    public JsonLstElement(String id, String type, String data) {
        this.id = id;
        this.type = type;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
