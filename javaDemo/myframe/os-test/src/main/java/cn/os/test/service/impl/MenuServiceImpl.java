package cn.os.test.service.impl;

import cn.os.test.common.config.result.ResultCode;
import cn.os.test.common.config.result.ResultStatus;
import cn.os.test.model.Menu;
import cn.os.test.repository.MenuRepository;
import cn.os.test.service.MenuService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    /**
     * 获取菜单列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public ResultCode list(Integer page, Integer pageSize) {
        JSONObject jsonObject = new JSONObject();
        Page<Menu> menuPage = menuRepository.findAll(Example.of(new Menu()), PageRequest.of(page, pageSize));
        jsonObject.put("list", menuPage.getContent());
        jsonObject.put("totalElements", menuPage.getTotalElements());
        return ResultCode.setSuccess(jsonObject);
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @Override
    public ResultCode deleteMenu(Menu menu) {
        menu.setEnabled(false);
        Menu save = menuRepository.save(menu);
        return save != null ?
                ResultCode.setSuccess(ResultStatus.DELETE_SUCCESS) :
                ResultCode.setFail(ResultStatus.DELETE_FAIL);
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @Override
    public ResultCode updateMenu(Menu menu) {
        Menu save = menuRepository.save(menu);
        return save != null ?
                ResultCode.setSuccess(ResultStatus.UPDATE_SUCCESS) :
                ResultCode.setFail(ResultStatus.UPDATE_FAIL);
    }

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @Override
    public ResultCode insertMenu(Menu menu) {
        Menu save = menuRepository.save(menu);
        return save != null ?
                ResultCode.setSuccess(ResultStatus.INSERT_SUCCESS) :
                ResultCode.setFail(ResultStatus.INSERT_FAIL);
    }
}
