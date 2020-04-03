package com.karljeong.fourtysix.common.loadstatic.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComAuth;
import com.karljeong.fourtysix.database.entity.TbComCode;
import com.karljeong.fourtysix.database.entity.TbComCodeGroup;
import com.karljeong.fourtysix.database.entity.TbComMenu;
import com.karljeong.fourtysix.database.repository.TbComAuthRepository;
import com.karljeong.fourtysix.database.repository.TbComCodeGroupRepository;
import com.karljeong.fourtysix.database.repository.TbComCodeRepository;
import com.karljeong.fourtysix.database.repository.TbComMenuRepository;
import com.karljeong.fourtysix.database.repository.TbMappPatternAuthRepository;

@Service
public class LoadStaticService {

    private final TbComCodeRepository tbComCodeRepository;
    private final TbComCodeGroupRepository tbComCodeGroupRepository;
    private final TbComMenuRepository tbComMenuRepository;
    private final TbComAuthRepository tbComAuthRepository;
    private final TbMappPatternAuthRepository tbMappPatternAuthRepository;

    @Autowired
	LoadStaticService(TbComCodeRepository tbComCodeRepository, TbComCodeGroupRepository tbComCodeGroupRepository, TbComMenuRepository tbComMenuRepository, TbComAuthRepository tbComAuthRepository, TbMappPatternAuthRepository tbMappPatternAuthRepository) {
		this.tbComCodeRepository = tbComCodeRepository;
		this.tbComCodeGroupRepository = tbComCodeGroupRepository;
		this.tbComMenuRepository = tbComMenuRepository;
		this.tbComAuthRepository = tbComAuthRepository;
		this.tbMappPatternAuthRepository = tbMappPatternAuthRepository;
	}

	public Map<String, Map<String, Object>> loadSystemCode() {
		Map<String, Map<String, Object>> systemCodes = new HashMap<String, Map<String, Object>>();

		List<TbComCode> tbComCodes = tbComCodeRepository.findByCodeGroupIdNotNull();

		Map<BigInteger, List<TbComCode>> systemCodeGrp = tbComCodes.stream()
				.collect(Collectors.groupingBy(c -> c.getCodeGroupId()));

		for (BigInteger codeGroupId : systemCodeGrp.keySet()) {
			Map<String, Object> comCodeGroupMap = new HashMap<String, Object>();
			TbComCodeGroup tbComCodeGroup = tbComCodeGroupRepository.findById(codeGroupId);
			comCodeGroupMap.put("codeGroupValue", tbComCodeGroup.getCodeGroupValue());
			comCodeGroupMap.put("codeGroupType", tbComCodeGroup.getCodeGroupType());
			comCodeGroupMap.put("codeGprId", tbComCodeGroup.getCodeGroupId());
			comCodeGroupMap.put("codeGprName", tbComCodeGroup.getCodeGroupName());

			Map<String, List<TbComCode>> systemCodeVal = systemCodeGrp.get(codeGroupId).stream()
					.collect(Collectors.groupingBy(c -> c.getCodeValue()));

			List<Map<String, Object>> codeList = new ArrayList<Map<String, Object>>();
			for (String codeValue : systemCodeVal.keySet()) {
				Map<String, Object> codeValInfo = new HashMap<String, Object>();
				codeValInfo.put("codeValue", codeValue);
				codeValInfo.put("codeValueId", systemCodeVal.get(codeValue).get(0).getCodeId());
				codeValInfo.put("codeName", systemCodeVal.get(codeValue).get(0).getCodeName());
				codeValInfo.put("codeOrder", systemCodeVal.get(codeValue).get(0).getCodeOrder());
				codeList.add(codeValInfo);

			}

			codeList.sort(Comparator.comparing(o -> (byte) o.get("codeOrder")));
			comCodeGroupMap.put("code", codeList);

			systemCodes.put(tbComCodeGroup.getCodeGroupValue(), comCodeGroupMap);
		}

		return systemCodes;
	}

    public List<Map<String, Object>> loadMenuType(String menuType) {
        List<TbComMenu> tbComMenus = tbComMenuRepository.findAll(menuType);
        return this.createHierarchyMenu(tbComMenus);
    }

	public List<Map<String, Object>> loadMenuNotType(String menuType) {
        List<TbComMenu> tbComMenus = tbComMenuRepository.findAllNotMenuType(menuType);
        return this.createHierarchyMenu(tbComMenus);

	}

	private List<Map<String, Object>> createHierarchyMenu(List<TbComMenu> tbComMenus) {
	    List<Map<String, Object>> hierarchyMenu = new ArrayList<Map<String, Object>>();

	    Map<String, Object> menuLv1 = new HashMap<String, Object>();
	    Map<String, Object> menuLv2 = new HashMap<String, Object>();
	    ArrayList<Map<String, Object>> menuLv2List = new ArrayList<Map<String, Object>>();
	    ArrayList<Map<String, Object>> menuLv3List = new ArrayList<Map<String, Object>>();

	    int prevHandledLevel = 0;
	    for (int i = 0 ; i < tbComMenus.size(); i++) {
	        TbComMenu tbComMenu = tbComMenus.get(i);

	        if (tbComMenu.getMenuLevel() == 1) {
	            if (prevHandledLevel > tbComMenu.getMenuLevel()) {
	                menuLv1.put("child", menuLv2List.clone());
	                menuLv2List.clear();
	                hierarchyMenu.add(menuLv1);
	            } else if (prevHandledLevel == tbComMenu.getMenuLevel()) {
	                menuLv2List.clear();
                    hierarchyMenu.add(menuLv1);
	            }else if ( ( i + 1 ) == tbComMenus.size() ) {
	                menuLv1 = this.setMenuProp(tbComMenu);
                    hierarchyMenu.add(menuLv1);
                }
	            menuLv1 = this.setMenuProp(tbComMenu);
	        } else if (tbComMenu.getMenuLevel() == 2) {
	            if (prevHandledLevel > tbComMenu.getMenuLevel()) {
	                menuLv2.put("child", menuLv3List.clone());
	                menuLv3List.clear();
	                menuLv2List.add(menuLv2);
	            }
	            menuLv2 = this.setMenuProp(tbComMenu);
	        } else if (tbComMenu.getMenuLevel() == 3) {
                menuLv3List.add(this.setMenuProp(tbComMenu));
	        }

	        if ((i + 1) == tbComMenus.size()) {
	            if ( tbComMenu.getMenuLevel() > 1 ) {
	                menuLv1.put("child", menuLv2List.clone());
	                hierarchyMenu.add(menuLv1);

	            } else if (tbComMenu.getMenuLevel() == 1) {
	                hierarchyMenu.add(menuLv1);
	            }
	        }

	        prevHandledLevel = tbComMenu.getMenuLevel();
	    }

        return hierarchyMenu;

    }

	private Map<String, Object> setMenuProp(TbComMenu tbComMenu) {
	    Map<String, Object> menu = new HashMap<String, Object>();
	    menu.put("menuId", tbComMenu.getMenuId());
	    menu.put("menuLevel", tbComMenu.getMenuLevel());
	    menu.put("menuName", tbComMenu.getMenuName());
	    menu.put("menuOrder", tbComMenu.getMenuOrder());
	    menu.put("menuPath", tbComMenu.getMenuPath());
	    menu.put("menuType", tbComMenu.getMenuType());
	    return menu;
	}

    public List<TbComAuth> loadAuthority() {
        return tbComAuthRepository.findAll();

    }

    public Map<String, List<String>> loadPatternList(){
        Map<String, List<String>> patterns = new HashMap<String, List<String>>();
        List<TbComAuth> tbComAuthList = tbComAuthRepository.findAll();
        for (int i = 0; i < tbComAuthList.size();i ++) {
            patterns.put("ROLE_" + tbComAuthList.get(i).getAuthCode(), new ArrayList<String>());
        }

        List<String[]> mappPatternAuthList = tbMappPatternAuthRepository.findAllPatternAuth();
        for (int i = 0; i < mappPatternAuthList.size(); i++) {
            List<String> uriPatterns = patterns.get("ROLE_" + mappPatternAuthList.get(i)[0]);
            uriPatterns.add(mappPatternAuthList.get(i)[1]);
            patterns.put("ROLE_" + mappPatternAuthList.get(i)[0], uriPatterns);
        }
        return patterns;
    }
}
