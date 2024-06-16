package pr2.tp.backen.ControlErrores;

import java.util.Map;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String message;
    private HttpStatus status;
    private Map<String, String> details;

    // Constructor básico
    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    // Constructor con detalles adicionales
    public ErrorResponse(String message, HttpStatus status, Map<String, String> details) {
        this.message = message;
        this.status = status;
        this.details = details;
    }

    public ErrorResponse(String inscripción_no_encontrada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

}
