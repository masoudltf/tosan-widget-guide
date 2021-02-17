package com.tosan.modern.widget.controller;

import com.tosan.modern.widget.model.Customer;
import com.tosan.modern.widget.model.Deposit;
import com.tosan.modern.widget.model.Layout;
import com.tosan.modern.widget.service.BoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Controller
@RequestMapping("/deposit")
@RequiredArgsConstructor
public class DepositController {

    private final BoomService boomService;

    @PostMapping("")
    public ModelAndView index(@RequestHeader("User-Agent") String userAgent, @ModelAttribute Customer customer) {
        Deposit[] deposits = boomService.getDeposits(userAgent, customer.getBankCode(), customer.getLoginToken(), customer.getSessionId());
        ModelAndView model = new ModelAndView("shared/layout");
        Layout layout = new Layout("deposit/index.jsp");
        model.addObject("layout", layout);
        model.addObject("deposits", deposits);

        return model;
    }

}
