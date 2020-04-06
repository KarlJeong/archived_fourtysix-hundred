package com.karljeong.fourtysix.common.loadstatic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karljeong.fourtysix.common.loadstatic.service.LoadStaticService;
import com.karljeong.fourtysix.database.entity.TbComAuth;

@Component
public class LoadStatic {

    private final LoadStaticService loadStaticService;

    @Autowired
    LoadStatic(LoadStaticService loadStaticService) {
        this.loadStaticService = loadStaticService;
    }

    private Map<String, Map<String, Object>> systemCode;
    private List<TbComAuth> authorityList;
    private List<Map<String, Object>> systemMenuList;
    private List<Map<String, Object>> serviceMenuList;
    private Map<String, ArrayList<List<String>>> patterList;

    @PostConstruct
    private void setSystemCode() {
        this.systemCode = loadStaticService.loadSystemCode();
    }

    public void resetSystemCode() {
        this.systemCode = loadStaticService.loadSystemCode();
    }

    public Map<String, Map<String, Object>> getSystemCode() {
        return systemCode;
    }

    @PostConstruct
    private void setAuthorityList() {
        this.authorityList = loadStaticService.loadAuthority();
    }

    public void resetAuthorityList() {
        this.authorityList = loadStaticService.loadAuthority();
    }

    public List<TbComAuth> getAuthorityList() {
        return authorityList;
    }

    @PostConstruct
    private void setSystemMenuList() {
        this.systemMenuList = loadStaticService.loadMenuType("SYSTEM");
    }

    public void resetSystemMenuList() {
        this.systemMenuList = loadStaticService.loadMenuType("SYSTEM");
    }

    public List<Map<String, Object>> getSystemMenuList() {
        return systemMenuList;
    }

    @PostConstruct
    private void setServiceMenuList() {
        this.serviceMenuList = loadStaticService.loadMenuNotType("SYSTEM");
    }

    public void resetServiceMenuList() {
        this.serviceMenuList = loadStaticService.loadMenuNotType("SYSTEM");
    }

    public List<Map<String, Object>> getServiceMenuList() {
        return serviceMenuList;
    }

    @PostConstruct
    private void setPatterList() {
        this.patterList = loadStaticService.loadPatternList();
    }

    public void resetPatterList() {
        this.patterList = loadStaticService.loadPatternList();
    }

    public Map<String, ArrayList<List<String>>> getPatterList() {
        return patterList;
    }

}
