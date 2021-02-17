package com.tosan.modern.widget.controller;

import com.tosan.modern.widget.model.Customer;
import com.tosan.modern.widget.model.Layout;
import com.tosan.modern.widget.service.BoomService;
import com.tosan.modern.widget.service.WistoreService;
import com.tosan.modern.widget.model.WistoreLoginToken;
import com.tosan.modern.widget.model.WistoreSSOToken;
import com.tosan.modern.widget.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Mohammad Abbasi
 * @since 15/02/2021
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final WistoreService wistoreService;
    private final BoomService boomService;

    @RequestMapping(value = {""}, method = {RequestMethod.HEAD})
    public HttpStatus headRequest() {
        return HttpStatus.OK;
    }

    @GetMapping("")
    public ModelAndView index(@RequestHeader("User-Agent") String userAgent, HttpSession session, @RequestParam String ssoJwt) throws InvalidJwtException {
        JwtClaims jwtClaims = JwtUtil.processJWT(ssoJwt);
        WistoreSSOToken wistoreSSOToken = WistoreSSOToken.builder().jwtClaims(jwtClaims).build();
        WistoreLoginToken wistoreLoginToken = setupWistoreLoginToken(userAgent);
        Customer customer = boomService.login(userAgent, wistoreSSOToken.getBankCode(), wistoreLoginToken.getToken(), wistoreSSOToken.getSsoToken());
        session.setAttribute("customer",customer);
        ModelAndView model = new ModelAndView("shared/layout");
        Layout layout = new Layout("home/index.jsp");
        model.addObject("layout", layout);
        model.addObject("customer", customer);

        return model;
    }

    private WistoreLoginToken setupWistoreLoginToken(String userAgent) {
        WistoreLoginToken wistoreLoginToken = wistoreService.getWistoreLoginToken();
        if (wistoreLoginToken == null) {
            wistoreLoginToken = wistoreService.login(userAgent);
            wistoreService.setWistoreLoginToken(wistoreLoginToken);
        }

        return wistoreLoginToken;
    }

}
