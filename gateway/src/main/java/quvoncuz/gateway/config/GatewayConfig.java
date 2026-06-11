//package quvoncuz.gateway.config;
//
//import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
//import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.function.RouterFunction;
//import org.springframework.web.servlet.function.ServerResponse;
//
//import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addRequestHeadersIfNotPresent;
//
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    public RouterFunction<ServerResponse> routeDefinition() {
//        return GatewayRouterFunctions.route("tour-agency-route")
//                .POST("/api/v1/**", HandlerFunctions.http("lb://tour-agency"))
//                .filter(addRequestHeadersIfNotPresent("Content-Type:application/json"))
//                .build();
//    }
//}