package edu.ifpb.dac.controlador;

import javax.faces.context.FacesContext;

public class UrlDispatcher {

    public static String dispatch(String path) {
        String external = FacesContext.getCurrentInstance()
                                      .getExternalContext()
                                      .getApplicationContextPath();
        return String.format("%s/%s?faces-redirect=true", external, path);
    }

}
