package in.bushansirgur.expensetrackerapi.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ErrorObject {

	private Integer statusCode;
	private String message;
	private Date timestamp;

}
