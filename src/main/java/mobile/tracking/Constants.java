package mobile.tracking;

import org.springframework.http.HttpStatus;

import java.util.EnumMap;

/**
 * Created by Hrisi on 10.11.2019 ã..
 */
public class Constants {

    public static final String DEFAULT_ERROR_MESSAGE = "System unable to process";
    public static EnumMap<ResponseType, HttpStatus> responseTypeMap = new EnumMap<>(ResponseType.class);

    static {
        responseTypeMap.put(ResponseType.SUCCESS, HttpStatus.OK);
        responseTypeMap.put(ResponseType.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        responseTypeMap.put(ResponseType.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
