package pl.pzdev2.scanner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pzdev2.scan.Scan;
import pl.pzdev2.scanner.interfaces.ScannerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class ScannerController {

    private ScannerHandler scannerHandler;

    public ScannerController(ScannerHandler scannerHandler) {
        this.scannerHandler = scannerHandler;
    }

    @PostMapping("/receive-books-barcode")
        public List<ScannerData> receiveBarcodeList(@RequestBody String json) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<ScannerData> listBarcode = objectMapper.readValue(json, new TypeReference<>() {
        });

        scannerHandler.receiveScans(listBarcode);
        return listBarcode;
    }

//    @RequestMapping
//    public List<ScannerData> receiveBarcode() throws JsonParseException, JsonMappingException, IOException, InterruptedException {
//
//        System.out.println("1");
//        try (var s = new ServerSocket(8189)) {
//            System.out.println("2");
//            try (Socket incoming = s.accept()) {
//                System.out.println("3");
//                String input = incoming.getInputStream().toString();
//                InputStream inputStream = incoming.getInputStream();
////                OutputStream outputStream = incoming.getOutputStream();
//
//                System.out.println("4");
////                var in = new Scanner(inputStream, StandardCharsets.UTF_8);
//                var mapper = new ObjectMapper();
//                System.out.println("5");
////                List<ScannerData> scannerData = mapper.readValue(input, new TypeReference<List<ScannerData>>(){});
//                System.out.println("6");
//                List<ScannerData> scannerData = new ArrayList<>();
//                scannerData.add(new ScannerData("barcode1", "createDate1"));
//                scannerData.add(new ScannerData("barcode2", "createDate2"));
//                return scannerData;
//            }
//
//        }
//
//    }

}
