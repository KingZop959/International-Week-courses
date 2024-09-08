package com.example.devicegps.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName zengliyue_gps_realtime
 */
@TableName(value ="zengliyue_gps_realtime")
@Data
public class GpsRealtime implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */

    private String deviceId;

    /**
     * 
     */
    private String deviceTime;

    /**
     * 
     */
    private String gpsTime;

    /**
     * 
     */
    private String recvTime;

    /**
     * 
     */
    private String longitude;

    /**
     * 
     */
    private String latitude;

    /**
     * 
     */
    private String altitude;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private String direction;

    /**
     * 
     */
    private String speed;

    /**
     * 
     */
    private String energy;

    /**
     * 
     */
    private String dataSource;

    /**
     * 
     */
    private String coordinateType;

    /**
     * 
     */
    private String creator;

    /**
     * 
     */
    private String creatorId;

    /**
     * 
     */
    private String createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GpsRealtime other = (GpsRealtime) that;
        return (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getDeviceTime() == null ? other.getDeviceTime() == null : this.getDeviceTime().equals(other.getDeviceTime()))
            && (this.getGpsTime() == null ? other.getGpsTime() == null : this.getGpsTime().equals(other.getGpsTime()))
            && (this.getRecvTime() == null ? other.getRecvTime() == null : this.getRecvTime().equals(other.getRecvTime()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getAltitude() == null ? other.getAltitude() == null : this.getAltitude().equals(other.getAltitude()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getDirection() == null ? other.getDirection() == null : this.getDirection().equals(other.getDirection()))
            && (this.getSpeed() == null ? other.getSpeed() == null : this.getSpeed().equals(other.getSpeed()))
            && (this.getEnergy() == null ? other.getEnergy() == null : this.getEnergy().equals(other.getEnergy()))
            && (this.getDataSource() == null ? other.getDataSource() == null : this.getDataSource().equals(other.getDataSource()))
            && (this.getCoordinateType() == null ? other.getCoordinateType() == null : this.getCoordinateType().equals(other.getCoordinateType()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getDeviceTime() == null) ? 0 : getDeviceTime().hashCode());
        result = prime * result + ((getGpsTime() == null) ? 0 : getGpsTime().hashCode());
        result = prime * result + ((getRecvTime() == null) ? 0 : getRecvTime().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getAltitude() == null) ? 0 : getAltitude().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getDirection() == null) ? 0 : getDirection().hashCode());
        result = prime * result + ((getSpeed() == null) ? 0 : getSpeed().hashCode());
        result = prime * result + ((getEnergy() == null) ? 0 : getEnergy().hashCode());
        result = prime * result + ((getDataSource() == null) ? 0 : getDataSource().hashCode());
        result = prime * result + ((getCoordinateType() == null) ? 0 : getCoordinateType().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceId=").append(deviceId);
        sb.append(", deviceTime=").append(deviceTime);
        sb.append(", gpsTime=").append(gpsTime);
        sb.append(", recvTime=").append(recvTime);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", altitude=").append(altitude);
        sb.append(", location=").append(location);
        sb.append(", direction=").append(direction);
        sb.append(", speed=").append(speed);
        sb.append(", energy=").append(energy);
        sb.append(", dataSource=").append(dataSource);
        sb.append(", coordinateType=").append(coordinateType);
        sb.append(", creator=").append(creator);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}