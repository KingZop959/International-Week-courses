package com.example.devicegps.controller;

import com.example.devicegps.domain.GpsRealtime;
import com.example.devicegps.service.GpsRealtimeService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zly
 */
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private GpsRealtimeService gpsRealtimeService;
    @PostMapping
    public void insert(@RequestBody GpsRealtime device){
        System.out.println(device);
        boolean save = gpsRealtimeService.save(device);
        System.out.println(save);
    }
    @GetMapping
    public List<GpsRealtime> getAll(){
        List<GpsRealtime> list = gpsRealtimeService.list();
        return list;
    }
    @GetMapping("/{id}")
    public GpsRealtime getById(@PathVariable Long id){
        GpsRealtime byId = gpsRealtimeService.getById(id);
        return byId;
    }
    @PostMapping("update")
    public void update(@Param("id") Long id,@Param("deviceId") String deviceId){
        GpsRealtime gpsRealtime = new GpsRealtime();
        gpsRealtime.setId(id);
        gpsRealtime.setDeviceId(deviceId);
        gpsRealtimeService.updateById(gpsRealtime);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        gpsRealtimeService.removeById(id);
    }

}
