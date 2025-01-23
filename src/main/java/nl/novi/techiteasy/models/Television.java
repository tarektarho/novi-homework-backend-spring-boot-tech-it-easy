package nl.novi.techiteasy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.Year;
import java.util.*;

@Entity
@Table(name = "television")
public class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type cannot be empty")
    private String type;

    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than 0")
    @Max(value = 10000, message = "Price must be less than 10000")
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private boolean smartTv = false;
    private boolean wifi = false;
    private boolean voiceControl = false;
    private boolean hdr = false;
    private boolean bluetooth = false;
    private boolean ambiLight = false;
    private Integer originalStock = 0;
    private Integer sold = 0;
    private String soldAt;
    private String boughtAt;
    private final Date createdAt = new Date();


    @OneToOne
    private RemoteController remoteController;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ci_module_id")
    private CIModule ciModule;

    @OneToMany(mappedBy = "television")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private Collection<TelevisionWallBracket> televisionWallBrackets;

    public Television() {
    }

    public Television(String type, String brand, String name, Double price, Double availableSize, Double refreshRate, String screenType, String screenQuality, boolean smartTv, boolean wifi, boolean voiceControl, boolean hdr, boolean bluetooth, boolean ambiLight, Integer originalStock, Integer sold, String soldAt, String boughtAt) {
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.availableSize = availableSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
        this.screenQuality = screenQuality;
        this.smartTv = smartTv;
        this.wifi = wifi;
        this.voiceControl = voiceControl;
        this.hdr = hdr;
        this.bluetooth = bluetooth;
        this.ambiLight = ambiLight;
        this.originalStock = originalStock;
        this.sold = sold;
        this.soldAt = soldAt;
        this.boughtAt = boughtAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAvailableSize() {
        return availableSize;
    }

    public void setAvailableSize(Double availableSize) {
        this.availableSize = availableSize;
    }

    public Double getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public boolean isSmartTv() {
        return smartTv;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isVoiceControl() {
        return voiceControl;
    }

    public void setVoiceControl(boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public boolean isHdr() {
        return hdr;
    }

    public void setHdr(boolean hdr) {
        this.hdr = hdr;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isAmbiLight() {
        return ambiLight;
    }

    public void setAmbiLight(boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public String getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(String soldAt) {
        this.soldAt = soldAt;
    }

    public String getBoughtAt() {
        return boughtAt;
    }

    public void setBoughtAt(String boughtAt) {
        this.boughtAt = boughtAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getAge() {

        if (boughtAt != null) {
            return Year.now().getValue() - Integer.parseInt(boughtAt);
        } else {
            return 0;
        }
    }

    public void setRemoteController(RemoteController remoteController) {
        this.remoteController = remoteController;
        if (remoteController != null) {
            remoteController.setTelevision(this);
        }
    }

    public RemoteController getRemoteController() {
        return remoteController;
    }

    public CIModule getCiModule() {
        return ciModule;
    }

    public void setCiModule(CIModule ciModule) {
        this.ciModule = ciModule;
    }

    public Collection<TelevisionWallBracket> getWallBrackets() {
        return televisionWallBrackets;
    }

    public void setWallBrackets(List<TelevisionWallBracket> televisionWallBrackets) {
        this.televisionWallBrackets = televisionWallBrackets;
    }
}
