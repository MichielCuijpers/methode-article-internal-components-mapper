package com.ft.methodearticleinternalcomponentsmapper.transformation;

import com.ft.bodyprocessing.xml.eventhandlers.RetainXMLEventHandler;
import com.ft.bodyprocessing.xml.eventhandlers.XMLEventHandlerRegistry;
import com.ft.methodearticleinternalcomponentsmapper.util.ApiUriGenerator;
import com.ft.methodearticleinternalcomponentsmapper.util.ConfiguredUriGenerator;
import com.ft.methodearticleinternalcomponentsmapper.util.UriBuilder;

import com.google.common.net.HostAndPort;

import java.util.Map;

public class ModelBodyFTTagsLinkRewriteXmlEventHandlerRegistry extends XMLEventHandlerRegistry {

    public ModelBodyFTTagsLinkRewriteXmlEventHandlerRegistry(final Map<String, String> contentTypeTemplates, final String apiHost) {
        UriBuilder uriBuilder = new UriBuilder(contentTypeTemplates);
        ApiUriGenerator apiUriGenerator = new ConfiguredUriGenerator(setSecurePort(apiHost));

        registerDefaultEventHandler(new RetainXMLEventHandler());

        registerStartAndEndElementEventHandler(new RewriteLinkXMLEventHandler("ft-content", uriBuilder, apiUriGenerator), "content");
        registerStartAndEndElementEventHandler(new RewriteLinkXMLEventHandler("ft-related", uriBuilder, apiUriGenerator), "related");
        registerStartAndEndElementEventHandler(new RewriteLinkXMLEventHandler("ft-concept", uriBuilder, apiUriGenerator), "concept");
    }

    private String setSecurePort(String host) {
        HostAndPort hostAndPort = HostAndPort.fromString(host);
        return hostAndPort.getHostText() + ":443";
    }
}
