package com.example.olexandr.conventerlab.model;

import java.util.List;
import java.util.Map;

public class JsonModel {


    private String sourceId;
    private String date;
    private List<OrganizationsEntity> organizations;
    private Map<String, Object> cities;
    private Map<String, Object> regions;
    private Map<String, Object> currencies;

    public Map<String, Object> getCurrencies() {
        return currencies;
    }

    public Map<String, Object> getRegions() {
        return regions;
    }

    public Map<String, Object> getCities() {
        return cities;
    }

    public void setCities(Map<String, Object> cities) {
        this.cities = cities;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrganizations(List<OrganizationsEntity> organizations) {
        this.organizations = organizations;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getDate() {
        return date;
    }

    public List<OrganizationsEntity> getOrganizations() {
        return organizations;
    }

    public static class OrganizationsEntity {


        private int orgType;
        private String address;
        private String regionId;
        private String phone;
        private String link;
        private String id;
        private String cityId;
        private int oldId;
        private String title;
        private CurrenciesEntity currencies;

        public void setOrgType(int orgType) {
            this.orgType = orgType;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public void setOldId(int oldId) {
            this.oldId = oldId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCurrencies(CurrenciesEntity currencies) {
            this.currencies = currencies;
        }

        public int getOrgType() {
            return orgType;
        }

        public String getAddress() {
            return address;
        }

        public String getRegionId() {
            return regionId;
        }

        public String getPhone() {
            return phone;
        }

        public String getLink() {
            return link;
        }

        public String getId() {
            return id;
        }

        public String getCityId() {
            return cityId;
        }

        public int getOldId() {
            return oldId;
        }

        public String getTitle() {
            return title;
        }

        public CurrenciesEntity getCurrencies() {
            return currencies;
        }

        public static class CurrenciesEntity {

            private CHFEntity CHF;
            private HRKEntity HRK;
            private MXNEntity MXN;
            private LVLEntity LVL;
            private MTLEntity MTL;
            private CLPEntity CLP;
            private VNDEntity VND;
            private AUDEntity AUD;
            private ILSEntity ILS;
            private MDLEntity MDL;
            private AMDEntity AMD;
            private TRYEntity TRY;
            private CYPEntity CYP;
            private LBPEntity LBP;
            private TJSEntity TJS;
            private AEDEntity AED;
            private HKDEntity HKD;
            private TWDEntity TWD;
            private EUREntity EUR;
            private DKKEntity DKK;
            private CADEntity CAD;
            private BGNEntity BGN;
            private EEKEntity EEK;
            private NOKEntity NOK;
            private ROLEntity ROL;
            private GELEntity GEL;
            private AZNEntity AZN;
            private CZKEntity CZK;
            private PKREntity PKR;
            private SEKEntity SEK;
            private KZTEntity KZT;
            private LTLEntity LTL;
            private SAREntity SAR;
            private INREntity INR;
            private CNYEntity CNY;
            private THBEntity THB;
            private KRWEntity KRW;
            private JPYEntity JPY;
            private PLNEntity PLN;
            private GBPEntity GBP;
            private HUFEntity HUF;
            private KWDEntity KWD;
            private BYREntity BYR;
            private RUBEntity RUB;
            private ISKEntity ISK;
            private USDEntity USD;
            private EGPEntity EGP;
            private SGDEntity SGD;
            private SKKEntity SKK;
            private NZDEntity NZD;
            private TMTEntity TMT;
            private BRLEntity BRL;

            public void setCHF(CHFEntity CHF) {
                this.CHF = CHF;
            }

            public void setHRK(HRKEntity HRK) {
                this.HRK = HRK;
            }

            public void setMXN(MXNEntity MXN) {
                this.MXN = MXN;
            }

            public void setLVL(LVLEntity LVL) {
                this.LVL = LVL;
            }

            public void setMTL(MTLEntity MTL) {
                this.MTL = MTL;
            }

            public void setCLP(CLPEntity CLP) {
                this.CLP = CLP;
            }

            public void setVND(VNDEntity VND) {
                this.VND = VND;
            }

            public void setAUD(AUDEntity AUD) {
                this.AUD = AUD;
            }

            public void setILS(ILSEntity ILS) {
                this.ILS = ILS;
            }

            public void setMDL(MDLEntity MDL) {
                this.MDL = MDL;
            }

            public void setAMD(AMDEntity AMD) {
                this.AMD = AMD;
            }

            public void setTRY(TRYEntity TRY) {
                this.TRY = TRY;
            }

            public void setCYP(CYPEntity CYP) {
                this.CYP = CYP;
            }

            public void setLBP(LBPEntity LBP) {
                this.LBP = LBP;
            }

            public void setTJS(TJSEntity TJS) {
                this.TJS = TJS;
            }

            public void setAED(AEDEntity AED) {
                this.AED = AED;
            }

            public void setHKD(HKDEntity HKD) {
                this.HKD = HKD;
            }

            public void setTWD(TWDEntity TWD) {
                this.TWD = TWD;
            }

            public void setEUR(EUREntity EUR) {
                this.EUR = EUR;
            }

            public void setDKK(DKKEntity DKK) {
                this.DKK = DKK;
            }

            public void setCAD(CADEntity CAD) {
                this.CAD = CAD;
            }

            public void setBGN(BGNEntity BGN) {
                this.BGN = BGN;
            }

            public void setEEK(EEKEntity EEK) {
                this.EEK = EEK;
            }

            public void setNOK(NOKEntity NOK) {
                this.NOK = NOK;
            }

            public void setROL(ROLEntity ROL) {
                this.ROL = ROL;
            }

            public void setGEL(GELEntity GEL) {
                this.GEL = GEL;
            }

            public void setAZN(AZNEntity AZN) {
                this.AZN = AZN;
            }

            public void setCZK(CZKEntity CZK) {
                this.CZK = CZK;
            }

            public void setPKR(PKREntity PKR) {
                this.PKR = PKR;
            }

            public void setSEK(SEKEntity SEK) {
                this.SEK = SEK;
            }

            public void setKZT(KZTEntity KZT) {
                this.KZT = KZT;
            }

            public void setLTL(LTLEntity LTL) {
                this.LTL = LTL;
            }

            public void setSAR(SAREntity SAR) {
                this.SAR = SAR;
            }

            public void setINR(INREntity INR) {
                this.INR = INR;
            }

            public void setCNY(CNYEntity CNY) {
                this.CNY = CNY;
            }

            public void setTHB(THBEntity THB) {
                this.THB = THB;
            }

            public void setKRW(KRWEntity KRW) {
                this.KRW = KRW;
            }

            public void setJPY(JPYEntity JPY) {
                this.JPY = JPY;
            }

            public void setPLN(PLNEntity PLN) {
                this.PLN = PLN;
            }

            public void setGBP(GBPEntity GBP) {
                this.GBP = GBP;
            }

            public void setHUF(HUFEntity HUF) {
                this.HUF = HUF;
            }

            public void setKWD(KWDEntity KWD) {
                this.KWD = KWD;
            }

            public void setBYR(BYREntity BYR) {
                this.BYR = BYR;
            }

            public void setRUB(RUBEntity RUB) {
                this.RUB = RUB;
            }

            public void setISK(ISKEntity ISK) {
                this.ISK = ISK;
            }

            public void setUSD(USDEntity USD) {
                this.USD = USD;
            }

            public void setEGP(EGPEntity EGP) {
                this.EGP = EGP;
            }

            public void setSGD(SGDEntity SGD) {
                this.SGD = SGD;
            }

            public void setSKK(SKKEntity SKK) {
                this.SKK = SKK;
            }

            public void setNZD(NZDEntity NZD) {
                this.NZD = NZD;
            }

            public void setTMT(TMTEntity TMT) {
                this.TMT = TMT;
            }

            public void setBRL(BRLEntity BRL) {
                this.BRL = BRL;
            }

            public CHFEntity getCHF() {
                return CHF;
            }

            public HRKEntity getHRK() {
                return HRK;
            }

            public MXNEntity getMXN() {
                return MXN;
            }

            public LVLEntity getLVL() {
                return LVL;
            }

            public MTLEntity getMTL() {
                return MTL;
            }

            public CLPEntity getCLP() {
                return CLP;
            }

            public VNDEntity getVND() {
                return VND;
            }

            public AUDEntity getAUD() {
                return AUD;
            }

            public ILSEntity getILS() {
                return ILS;
            }

            public MDLEntity getMDL() {
                return MDL;
            }

            public AMDEntity getAMD() {
                return AMD;
            }

            public TRYEntity getTRY() {
                return TRY;
            }

            public CYPEntity getCYP() {
                return CYP;
            }

            public LBPEntity getLBP() {
                return LBP;
            }

            public TJSEntity getTJS() {
                return TJS;
            }

            public AEDEntity getAED() {
                return AED;
            }

            public HKDEntity getHKD() {
                return HKD;
            }

            public TWDEntity getTWD() {
                return TWD;
            }

            public EUREntity getEUR() {
                return EUR;
            }

            public DKKEntity getDKK() {
                return DKK;
            }

            public CADEntity getCAD() {
                return CAD;
            }

            public BGNEntity getBGN() {
                return BGN;
            }

            public EEKEntity getEEK() {
                return EEK;
            }

            public NOKEntity getNOK() {
                return NOK;
            }

            public ROLEntity getROL() {
                return ROL;
            }

            public GELEntity getGEL() {
                return GEL;
            }

            public AZNEntity getAZN() {
                return AZN;
            }

            public CZKEntity getCZK() {
                return CZK;
            }

            public PKREntity getPKR() {
                return PKR;
            }

            public SEKEntity getSEK() {
                return SEK;
            }

            public KZTEntity getKZT() {
                return KZT;
            }

            public LTLEntity getLTL() {
                return LTL;
            }

            public SAREntity getSAR() {
                return SAR;
            }

            public INREntity getINR() {
                return INR;
            }

            public CNYEntity getCNY() {
                return CNY;
            }

            public THBEntity getTHB() {
                return THB;
            }

            public KRWEntity getKRW() {
                return KRW;
            }

            public JPYEntity getJPY() {
                return JPY;
            }

            public PLNEntity getPLN() {
                return PLN;
            }

            public GBPEntity getGBP() {
                return GBP;
            }

            public HUFEntity getHUF() {
                return HUF;
            }

            public KWDEntity getKWD() {
                return KWD;
            }

            public BYREntity getBYR() {
                return BYR;
            }

            public RUBEntity getRUB() {
                return RUB;
            }

            public ISKEntity getISK() {
                return ISK;
            }

            public USDEntity getUSD() {
                return USD;
            }

            public EGPEntity getEGP() {
                return EGP;
            }

            public SGDEntity getSGD() {
                return SGD;
            }

            public SKKEntity getSKK() {
                return SKK;
            }

            public NZDEntity getNZD() {
                return NZD;
            }

            public TMTEntity getTMT() {
                return TMT;
            }

            public BRLEntity getBRL() {
                return BRL;
            }

            public static class CHFEntity {

                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class HRKEntity {

                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class MXNEntity {

                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class LVLEntity {

                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class MTLEntity {

                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class CLPEntity {
                /**
                 * ask : 0.0470
                 * bid : 0.0275
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class VNDEntity {
                /**
                 * ask : 0.0012
                 * bid : 0.0010
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class AUDEntity {
                /**
                 * ask : 16.2800
                 * bid : 14.6070
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class ILSEntity {
                /**
                 * ask : 6.2000
                 * bid : 5.5070
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class MDLEntity {
                /**
                 * ask : 1.1900
                 * bid : 0.9120
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class AMDEntity {
                /**
                 * ask : 0.0500
                 * bid : 0.0285
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class TRYEntity {
                /**
                 * ask : 8.0000
                 * bid : 7.0080
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class CYPEntity {
                /**
                 * ask : 13.2000
                 * bid : 10.2050
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class LBPEntity {
                /**
                 * ask : 0.0190
                 * bid : 0.0115
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class TJSEntity {
                /**
                 * ask : 5.0000
                 * bid : 2.0150
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class AEDEntity {
                /**
                 * ask : 6.3800
                 * bid : 5.6300
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class HKDEntity {
                /**
                 * ask : 3.4000
                 * bid : 2.0250
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class TWDEntity {
                /**
                 * ask : 0.8800
                 * bid : 0.4500
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class EUREntity {
                /**
                 * ask : 25.9400
                 * bid : 25.3000
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class DKKEntity {
                /**
                 * ask : 3.4200
                 * bid : 3.1050
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class CADEntity {
                /**
                 * ask : 17.5500
                 * bid : 16.6070
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class BGNEntity {
                /**
                 * ask : 14.0000
                 * bid : 8.0950
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class EEKEntity {
                /**
                 * ask : 1.8000
                 * bid : 0.4550
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class NOKEntity {
                /**
                 * ask : 2.7800
                 * bid : 2.5150
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class ROLEntity {
                /**
                 * ask : 5.8900
                 * bid : 4.6250
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class GELEntity {
                /**
                 * ask : 12.3000
                 * bid : 7.1570
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class AZNEntity {
                /**
                 * ask : 21.6000
                 * bid : 15.0700
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class CZKEntity {
                /**
                 * ask : 0.9650
                 * bid : 0.9150
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class PKREntity {
                /**
                 * ask : 0.3000
                 * bid : 0.1350
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class SEKEntity {
                /**
                 * ask : 2.7200
                 * bid : 2.4700
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class KZTEntity {
                /**
                 * ask : 0.0840
                 * bid : 0.0560
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class LTLEntity {
                /**
                 * ask : 7.3000
                 * bid : 5.1300
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class SAREntity {
                /**
                 * ask : 6.1500
                 * bid : 4.1800
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class INREntity {
                /**
                 * ask : 0.3500
                 * bid : 0.2270
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class CNYEntity {
                /**
                 * ask : 3.5800
                 * bid : 3.0050
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class THBEntity {
                /**
                 * ask : 0.6600
                 * bid : 0.4100
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class KRWEntity {
                /**
                 * ask : 0.0220
                 * bid : 0.0135
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class JPYEntity {
                /**
                 * ask : 0.1870
                 * bid : 0.1705
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class PLNEntity {
                /**
                 * ask : 6.2900
                 * bid : 6.1520
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class GBPEntity {
                /**
                 * ask : 35.6500
                 * bid : 34.5030
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class HUFEntity {
                /**
                 * ask : 0.0850
                 * bid : 0.0807
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class KWDEntity {
                /**
                 * ask : 73.0000
                 * bid : 46.1000
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class BYREntity {
                /**
                 * ask : 0.0016
                 * bid : 0.0013
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class RUBEntity {
                /**
                 * ask : 0.3390
                 * bid : 0.3100
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class ISKEntity {
                /**
                 * ask : 0.1780
                 * bid : 0.1135
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class USDEntity {
                /**
                 * ask : 22.9400
                 * bid : 22.5000
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class EGPEntity {
                /**
                 * ask : 2.8800
                 * bid : 1.7570
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class SGDEntity {
                /**
                 * ask : 16.9000
                 * bid : 13.2700
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class SKKEntity {
                /**
                 * ask : 0.6800
                 * bid : 0.3570
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class NZDEntity {
                /**
                 * ask : 17.0000
                 * bid : 11.1300
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class TMTEntity {
                /**
                 * ask : 7.0000
                 * bid : 3.2050
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }

            public static class BRLEntity {
                /**
                 * ask : 7.4000
                 * bid : 4.0580
                 */
                private String ask;
                private String bid;

                public void setAsk(String ask) {
                    this.ask = ask;
                }

                public void setBid(String bid) {
                    this.bid = bid;
                }

                public String getAsk() {
                    return ask;
                }

                public String getBid() {
                    return bid;
                }
            }
        }


    }
}
