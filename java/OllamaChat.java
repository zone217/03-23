import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OllamaChat {
    private static final String OLLAMA_API = "http://localhost:11434";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();
    
    public static String chat(String prompt) throws IOException, InterruptedException {
        String json = String.format(
            "{\"model\":\"gemma3:4b\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}]}",
            prompt
        );
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(OLLAMA_API + "/api/chat"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode result = mapper.readTree(response.body());
        return result.get("message").get("content").asText();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Ollama Java 示例 ===");
        try {
            String result = chat("你好，请介绍一下自己");
            System.out.println("回复：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
