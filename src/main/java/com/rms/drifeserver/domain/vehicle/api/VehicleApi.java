package com.rms.drifeserver.domain.vehicle.api;

import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.vehicle.model.Vehicle;
import com.rms.drifeserver.domain.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleApi {
    private final VehicleService vehicleService;

    @PostMapping
    public Map<String,Object> enrollVehicle(@RequestBody Vehicle vehicle) {
        Map<String,Object> ret = new HashMap<String,Object>();
        Optional<Vehicle> isExist=vehicleService.findVehicleByNum(vehicle.getVehicleNum());
        try{
            if(!isExist.isEmpty()){
                ret.put("state","fail");
                ret.put("message","userId (: "+isExist.get().getVehicleNum()+" ) is already exist");
            }else{
                vehicleService.saveVehicle(vehicle);
                Optional<Vehicle> retObj = vehicleService.findVehicleByNum(vehicle.getVehicleNum());
                if(retObj==null){
                    throw new Exception("회원가입 실패");
                }
                ret.put("state","success");
                ret.put("result",retObj);
            }
        }catch(Exception e){
            ret.put("state","fail");
            ret.put("message",e.getMessage());
        }finally {
            return ret;
        }
    }

    @GetMapping("/{vehicle_num}")
    public  Map<String,Object> findVehicleByNum(@PathVariable("vehicle_num") String vehicleNum) {
        Map<String,Object> ret = new HashMap<String,Object>();
        try{
            Optional<Vehicle> retObj=vehicleService.findVehicleByNum(vehicleNum);
            if(retObj.isPresent()){
                ret.put("state","success");
                ret.put("result",retObj);
            }else{
                ret.put("state","empty");
                ret.put("result","");
            }
        }catch (Exception e){
            ret.put("state","error");
            ret.put("message",e.getMessage());
        }finally {
            return ret;
        }
    }

    @DeleteMapping("/{vehicle_num}")
    public  void deleteVehicle(@PathVariable("vehicle_num") String vehicleNum) {
        vehicleService.deleteVehicle(vehicleNum);
    }
}
