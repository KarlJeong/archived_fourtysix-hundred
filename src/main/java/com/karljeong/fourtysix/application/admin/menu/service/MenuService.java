package com.karljeong.fourtysix.application.admin.menu.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karljeong.fourtysix.database.entity.TbComMenu;
import com.karljeong.fourtysix.database.repository.TbComMenuRepository;

@Service
public class MenuService {

    private final TbComMenuRepository tbComMenuRepository;

    @Autowired
	MenuService(TbComMenuRepository tbComMenuRepository) {
	    this.tbComMenuRepository = tbComMenuRepository;
	}

	public List<TbComMenu> findAll() {
	    List<TbComMenu> tbComMenus = tbComMenuRepository.findAll();
	    for (TbComMenu tbComMenu : tbComMenus) {

	        TbComMenu tbComMenuPrior = tbComMenus.stream().filter(d -> d.getMenuId() == tbComMenu.getPriorMenuId()).findAny().orElse(null);

	        if (tbComMenuPrior != null) {
	            tbComMenu.setPriorMenuName(tbComMenuPrior.getMenuName());
	        }
	    }
        return tbComMenus;
    }

    public TbComMenu create(TbComMenu tbComMenu) {
        tbComMenu.setCreateUserId(BigInteger.valueOf(11111));
        return tbComMenuRepository.save(tbComMenu);
    }

    public TbComMenu update(TbComMenu tbComMenu) {
        tbComMenu.setUpdateUserId(BigInteger.valueOf(11111));
        return tbComMenuRepository.save(tbComMenu);
    }

    public void delete(BigInteger menuId) {
        tbComMenuRepository.deleteById(menuId);
    }

}
