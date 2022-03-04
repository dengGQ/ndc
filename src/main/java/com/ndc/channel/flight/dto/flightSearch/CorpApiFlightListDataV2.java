package com.ndc.channel.flight.dto.flightSearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;


@ApiModel("航班信息V1.2")
public class CorpApiFlightListDataV2 {

	@ApiModelProperty(value = "航班ID", required = true)
	private String flightId;

	@ApiModelProperty(value = "航空公司代码", required = true)
	private String airlineCode;

	@ApiModelProperty(value = "航空公司简称", required = true)
	private String airlineShortName;

	@ApiModelProperty(value = "机建费", required = false)
	private BigDecimal buildFee;

	@ApiModelProperty(value = "出发机场", required = true)
	private String departureAirport;

	@ApiModelProperty(value = "出发机场三字码", required = true)
	private String departureAirportCode;

	@ApiModelProperty(value = "出发城市", required = true)
	private String departureCityName;

	@ApiModelProperty(value = "出发城市三字码", required = true)
	private String departureCityCode;

	@ApiModelProperty(value = "出发航站楼", required = false)
	private String departureTerminal;

	@ApiModelProperty(value = "出发时间", required = true)
	private String departureTime;

	@ApiModelProperty(value = "到达机场", required = true)
	private String destinationAirport;

	@ApiModelProperty(value = "到达机场三字码", required = true)
	private String destinationAirportCode;

	@ApiModelProperty(value = "到达城市三字码", required = true)
	private String destinationCityCode;

	@ApiModelProperty(value = "到达城市", required = true)
	private String destinationCityName;

	@ApiModelProperty(value = "到达航站楼", required = false)
	private String destinationTerminal;

	@ApiModelProperty(value = "到达时间", required = true)
	private String destinationTime;

	@ApiModelProperty(value = "航班日期", required = true)
	private String flightDate;

	@ApiModelProperty(value = "航班号", required = true)
	private String flightNumber;

	@ApiModelProperty(value = "飞行时间，HH:mm", required = true)
	private String flyingTime;

	@ApiModelProperty(value = "是否共享航班，0-否；1-是", required = false)
	private String isShare;

	@ApiModelProperty(value = "是否经停，0-否；1-是", required = false)
	private String isStopover;

	@ApiModelProperty(value = "共享航班的实际承运航空公司代码", required = false)
	private String mainAirlineCode;

	@ApiModelProperty(value = "承运航空公司简称", required = false)
	private String mainAirlineShortName;

	@ApiModelProperty(value = "承运航班号", required = false)
	private String mainFlightNumber;

	@ApiModelProperty(value = "餐饮类型，0-不含餐；1-含餐", required = false)
	private String mealType;

	@ApiModelProperty(value = "航班最低价", required = true)
	private BigDecimal minPrice;

	@ApiModelProperty(value = "燃油费", required = false)
	private BigDecimal oilFee;

	@ApiModelProperty(value = "机型", required = true)
	private String planeType;

	@ApiModelProperty(value = "最低价舱位类型 1-经济舱； 2-商务舱； 3-头等舱", required = true)
	private String seatType;

	@ApiModelProperty(value = "公里数", required = false)
	private String tpm;

	@ApiModelProperty(value = "经济舱标准价")
	private BigDecimal yClassPrice;
	@ApiModelProperty(value = "公务舱标准价")
	private BigDecimal cClassPrice;
	@ApiModelProperty(value = "头等舱标准价")
	private BigDecimal fClassPrice;

	@ApiModelProperty(value = "经停信息", required = false)
	private List<FlightStopOver> stopoverInfo;

	@ApiModelProperty(value = "机票信息", required = true)
	private List<CorpApiTicketData> tickets;

	private String ownerTypeCode;
	private String ownerCode;
	private String shoppingResponseID;

	private String originDestID;
	private String offerId;
	private List<String> paxJourneyRefID;
	private String paxJourneyID;
	private List<String> paxSegmentRefID;
	private String paxSegmentID;

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineShortName() {
		return airlineShortName;
	}

	public void setAirlineShortName(String airlineShortName) {
		this.airlineShortName = airlineShortName;
	}

	public BigDecimal getBuildFee() {
		return buildFee;
	}

	public void setBuildFee(BigDecimal buildFee) {
		this.buildFee = buildFee;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public String getDepartureCityName() {
		return departureCityName;
	}

	public void setDepartureCityName(String departureCityName) {
		this.departureCityName = departureCityName;
	}

	public String getDepartureCityCode() {
		return departureCityCode;
	}

	public void setDepartureCityCode(String departureCityCode) {
		this.departureCityCode = departureCityCode;
	}

	public String getDepartureTerminal() {
		return departureTerminal;
	}

	public void setDepartureTerminal(String departureTerminal) {
		this.departureTerminal = departureTerminal;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}

	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}

	public String getDestinationCityCode() {
		return destinationCityCode;
	}

	public void setDestinationCityCode(String destinationCityCode) {
		this.destinationCityCode = destinationCityCode;
	}

	public String getDestinationCityName() {
		return destinationCityName;
	}

	public void setDestinationCityName(String destinationCityName) {
		this.destinationCityName = destinationCityName;
	}

	public String getDestinationTerminal() {
		return destinationTerminal;
	}

	public void setDestinationTerminal(String destinationTerminal) {
		this.destinationTerminal = destinationTerminal;
	}

	public String getDestinationTime() {
		return destinationTime;
	}

	public void setDestinationTime(String destinationTime) {
		this.destinationTime = destinationTime;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlyingTime() {
		return flyingTime;
	}

	public void setFlyingTime(String flyingTime) {
		this.flyingTime = flyingTime;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public String getIsStopover() {
		return isStopover;
	}

	public void setIsStopover(String isStopover) {
		this.isStopover = isStopover;
	}

	public String getMainAirlineCode() {
		return mainAirlineCode;
	}

	public void setMainAirlineCode(String mainAirlineCode) {
		this.mainAirlineCode = mainAirlineCode;
	}

	public String getMainAirlineShortName() {
		return mainAirlineShortName;
	}

	public void setMainAirlineShortName(String mainAirlineShortName) {
		this.mainAirlineShortName = mainAirlineShortName;
	}

	public String getMainFlightNumber() {
		return mainFlightNumber;
	}

	public void setMainFlightNumber(String mainFlightNumber) {
		this.mainFlightNumber = mainFlightNumber;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getOilFee() {
		return oilFee;
	}

	public void setOilFee(BigDecimal oilFee) {
		this.oilFee = oilFee;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getTpm() {
		return tpm;
	}

	public void setTpm(String tpm) {
		this.tpm = tpm;
	}

	public BigDecimal getyClassPrice() {
		return yClassPrice;
	}

	public void setyClassPrice(BigDecimal yClassPrice) {
		this.yClassPrice = yClassPrice;
	}

	public BigDecimal getcClassPrice() {
		return cClassPrice;
	}

	public void setcClassPrice(BigDecimal cClassPrice) {
		this.cClassPrice = cClassPrice;
	}

	public BigDecimal getfClassPrice() {
		return fClassPrice;
	}

	public void setfClassPrice(BigDecimal fClassPrice) {
		this.fClassPrice = fClassPrice;
	}

	public List<FlightStopOver> getStopoverInfo() {
		return stopoverInfo;
	}

	public void setStopoverInfo(List<FlightStopOver> stopoverInfo) {
		this.stopoverInfo = stopoverInfo;
	}

	public List<CorpApiTicketData> getTickets() {
		return tickets;
	}

	public void setTickets(List<CorpApiTicketData> tickets) {
		this.tickets = tickets;
	}

	public String getShoppingResponseID() {
		return shoppingResponseID;
	}

	public void setShoppingResponseID(String shoppingResponseID) {
		this.shoppingResponseID = shoppingResponseID;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOriginDestID() {
		return originDestID;
	}

	public void setOriginDestID(String originDestID) {
		this.originDestID = originDestID;
	}

	public List<String> getPaxJourneyRefID() {
		return paxJourneyRefID;
	}

	public void setPaxJourneyRefID(List<String> paxJourneyRefID) {
		this.paxJourneyRefID = paxJourneyRefID;
	}

	public String getPaxJourneyID() {
		return paxJourneyID;
	}

	public void setPaxJourneyID(String paxJourneyID) {
		this.paxJourneyID = paxJourneyID;
	}

	public List<String> getPaxSegmentRefID() {
		return paxSegmentRefID;
	}

	public void setPaxSegmentRefID(List<String> paxSegmentRefID) {
		this.paxSegmentRefID = paxSegmentRefID;
	}

	public String getPaxSegmentID() {
		return paxSegmentID;
	}

	public void setPaxSegmentID(String paxSegmentID) {
		this.paxSegmentID = paxSegmentID;
	}

	public String getOwnerTypeCode() {
		return ownerTypeCode;
	}

	public void setOwnerTypeCode(String ownerTypeCode) {
		this.ownerTypeCode = ownerTypeCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}
}
