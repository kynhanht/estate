package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.dto.request.PaginationRequest;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;

public class DisplayTagUtils {

    public static Integer getPage(PaginationRequest request, HttpServletRequest httpServletRequest) {
        Integer page = 1;
        String sPage = httpServletRequest.getParameter(new ParamEncoder(request.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
        if (StringUtils.isNotBlank(sPage)) {
            page = Integer.valueOf(sPage);
        }
        return page;
    }

    public static String getSortColumnName(PaginationRequest request, HttpServletRequest httpServletRequest) {
        String sortColumnName = httpServletRequest.getParameter(new ParamEncoder(request.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
        if (StringUtils.isNotBlank(sortColumnName)) {
            return sortColumnName;
        }
        return null;
    }

    public static String getSortDirection(PaginationRequest request, HttpServletRequest httpServletRequest) {
        String sortDirection = httpServletRequest.getParameter(new ParamEncoder(request.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
        if (StringUtils.isNotBlank(sortDirection)) {
            if (sortDirection.equals("1")) {
                return "asc";
            } else if (sortDirection.equals("2")) {
                return "desc";
            } else {
                return null;
            }
        }
        return null;
    }

}
