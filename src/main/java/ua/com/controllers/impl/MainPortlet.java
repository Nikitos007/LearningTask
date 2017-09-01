package ua.com.controllers.impl;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@org.springframework.stereotype.Controller(value = "MainPortlet")
@RequestMapping("VIEW")
public class MainPortlet {

    @RenderMapping
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {
        return "defaultRender";
    }

}
