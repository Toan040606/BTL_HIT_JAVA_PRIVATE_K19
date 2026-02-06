package root.service.impl;

import root.constant.ErrorMessage;
import root.dao.AreaDao;
import root.dao.impl.AreaDaoImpl;
import root.model.entity.core.Area;
import root.model.entity.core.User;
import root.service.AreaService;
import root.util.UserSession;

public class AreaServiceImpl implements AreaService {
    AreaDao areaDao = new AreaDaoImpl();

    @Override
    public void createArea(String newAreaName) {
        if (newAreaName.isEmpty()) {
            System.out.println(ErrorMessage.EMPTY_ERROR);
            return;
        }

        Area area = Area.builder()
                .name(newAreaName)
                .user(UserSession.getCurrentUser())
                .build();

        if (areaDao.createArea(area)) {
            System.out.println("gud");
        } else {
            System.out.println("man");
        }
    }


}
