package com.chiShoAutom.RestControllers;

import com.chiShoAutom.HelpUtils.Mappers.ModelMappers.Mapper;
import com.chiShoAutom.Models.BitrixModels.BitrixUser;
import com.chiShoAutom.Models.Worker;
import com.chiShoAutom.RestServices.BitrixRestServices.UserRestBitrixService;
import com.chiShoAutom.Services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/rest/settings/users")
public class UserRestController {


    @Autowired
    private WorkerService workerService;

    @Autowired
    private UserRestBitrixService userRestBitrixService;

    @Autowired
    private Mapper<Worker, BitrixUser> mapper;

    @PostMapping("add")
    public void addUserWithPass(@RequestParam String email,
                                @RequestParam String pass) {


        if (!Objects.isNull(email) && !email.isEmpty()) {

            Optional<BitrixUser> bitrixUserOpt = userRestBitrixService.getByEmail(email);

            if (bitrixUserOpt.isPresent()) {

//                    Optional<User> userToSaveUpdateOpt = userService.findById(bitrixUserOpt.get().getId());
//
//                    if(userToSaveUpdateOpt.isPresent()){
//
//                        User bitrixUser = mapper.toEntity(bitrixUserOpt.get());
//
//                        User userFromDb = userToSaveUpdateOpt.get();
//
//                        userFromDb.setBitrixDepartmentsIds(bitrixUser.getBitrixDepartmentsIds());
//
//                        if (!Objects.isNull(pass)){
//                            boolean nPass = pass.contains(" ");
//                            if(!nPass) userService.saveUserWithPass(userFromDb,pass);
//                        }
//
//
//                    }

                Worker bitrixUser = mapper.toEntity(bitrixUserOpt.get());

                if (!Objects.isNull(pass)) {
                    boolean nPass = pass.contains(" ");
                    if (!nPass) workerService.saveUserWithPass(bitrixUser, pass);
                }

            }

        }
    }

}
