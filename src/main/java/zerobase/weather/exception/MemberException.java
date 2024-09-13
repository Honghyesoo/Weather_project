package zerobase.weather.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberException extends RuntimeException{
  public MemberException(String message){
    super(message);
  }

}
