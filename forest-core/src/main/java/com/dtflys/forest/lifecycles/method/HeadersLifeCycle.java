package com.dtflys.forest.lifecycles.method;

import com.dtflys.forest.annotation.Headers;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.lifecycles.MethodAnnotationLifeCycle;
import com.dtflys.forest.reflection.ForestMethod;
import com.dtflys.forest.reflection.MetaRequest;
import com.dtflys.forest.utils.ReflectUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;


public class HeadersLifeCycle implements MethodAnnotationLifeCycle<Headers, Object> {

    @Override
    public void onMethodInitialized(ForestMethod method, Headers annotation) {
        MetaRequest metaRequest = method.getMetaRequest();
        String[] headers = annotation.value();
        String[] oldHeaders = metaRequest.getHeaders();
        int len = headers.length + oldHeaders.length;
        String[] newHeaders = new String[headers.length + oldHeaders.length];
        for (int i = 0; i < oldHeaders.length; i++) {
            newHeaders[i] = oldHeaders[i];
        }
        for (int i = 0; i < headers.length; i++) {
            newHeaders[oldHeaders.length + i] = headers[i];
        }
//        String[] newHeaders = ArrayUtils.addAll(oldHeaders, headers);
        metaRequest.setHeaders(newHeaders);
    }

    @Override
    public boolean beforeExecute(ForestRequest request) {
        return true;
    }

}
