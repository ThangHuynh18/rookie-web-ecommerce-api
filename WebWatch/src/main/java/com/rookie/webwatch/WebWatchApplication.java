package com.rookie.webwatch;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.api.client.util.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class WebWatchApplication {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    public static void main(String[] args) {
        SpringApplication.run(WebWatchApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "thanghuynh");
        config.put("api_key", "391892355813339");
        config.put("api_secret", "plilYCVudZRIDth1pdnsfJ_fJ88");
        cloudinary = new Cloudinary(config);
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dnpwhgom3",
//                "api_key", "391892355813339",
//                "api_secret", "plilYCVudZRIDth1pdnsfJ_fJ88")); // insert here your api secret
//        SingletonManager manager = new SingletonManager();
//        manager.setCloudinary(cloudinary);
//        manager.init();
        return cloudinary;
    }
}
