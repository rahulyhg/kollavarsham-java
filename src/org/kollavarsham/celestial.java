package org.kollavarsham;

import java.util.HashMap;
import java.util.Map;

/*
 * kollavarsham
 * http://kollavarsham.org
 *
 * Copyright (c) 2014 The Kollavarsham Team
 * Licensed under the MIT license.
 */

public class celestial {

	Map primaryYugaRotationConstants;
	Map planetNames;
	math kvmath;
	Map YugaRotation;
	Map PlanetRotation;
	Map PlanetSighra;
	Map PlanetApogee;
	Map PlanetCircumm;
	Map PlanetCircums;
	Map PlanetMeanPosition;
	Map PlanetTruePosition;
	globals myGlobals;
	

	celestial(){
		myGlobals = globals.getInstance();
		primaryYugaRotationConstants = new HashMap<String, Long>();
		primaryYugaRotationConstants.put("star", 1582237800);
		primaryYugaRotationConstants.put("sun", 4320000);
		primaryYugaRotationConstants.put("moon", 57753336);
		primaryYugaRotationConstants.put("mercury", 17937000);
		primaryYugaRotationConstants.put("venus", 7022388);
		primaryYugaRotationConstants.put("mars", 2296824);
		primaryYugaRotationConstants.put("jupiter", 364220);
		primaryYugaRotationConstants.put("saturn", 146564);
		primaryYugaRotationConstants.put("Candrocca", 488219);
		primaryYugaRotationConstants.put("Rahu", -232226);

		planetNames = new HashMap<String, String>();
		planetNames.put("star", "Star        ");
		planetNames.put("sun", "Sun         ");
		planetNames.put("moon", "Moon        ");
		planetNames.put("mercury", "Mercury     ");
		planetNames.put("venus", "Venus       ");
		planetNames.put("mars", "Mars        ");
		planetNames.put("jupiter", "Jupiter     ");
		planetNames.put("saturn", "Saturn      ");
		planetNames.put("Candrocca", "Candrocca   ");
		planetNames.put("Rahu", "Rahu        ");
		YugaRotation = new HashMap<String, Long>();
		PlanetRotation = new HashMap<String, Long>();
		PlanetSighra = new HashMap<String, Long>();
		PlanetApogee = new HashMap<String, Long>();
		PlanetCircumm = new HashMap<String, Long>();
		PlanetCircums = new HashMap<String, Long>();
		PlanetMeanPosition = new HashMap<String, Double>();
		PlanetTruePosition = new HashMap<String, Double>();
		
	}

	public Double zero360(Double longitude) {
		Double result = longitude - kvmath.truncate(longitude/360)*360;
		return result < 0 ? 360 + result : result;
	}

	public Double threeRelation (Double leftY, Double centreY, Double rightY){
		if (leftY < centreY && centreY < rightY) {
			return 1.0;
		} else if (rightY < centreY && centreY < leftY) {
			return -1.0;
		}
		return 0.0;
	}

	public void setPrimaryConstants(){
		this.YugaRotation = primaryYugaRotationConstants;
	}


	public void applyBija() {
		Long star = (Long) this.YugaRotation.get("star");
		this.YugaRotation.put("star", star + 28);
		//this.YugaRotation.sun = this.YugaRotation.sun;  // reduntant line - kept for consistency
		//this.YugaRotation.moon = this.YugaRotation.moon;  // reduntant line - kept for consistency
		Long mercury = (Long) this.YugaRotation.get("mercury");
		this.YugaRotation.put("mercury", mercury + 60);

		Long venus = (Long) this.YugaRotation.get("venus");
		this.YugaRotation.put("venus", venus - 12);

		Long mars = (Long) this.YugaRotation.get("mars");
		this.YugaRotation.put("mars", mars + 8);

		//this.YugaRotation.jupiter = this.YugaRotation.jupiter;  // reduntant line - kept for consistency

		Long saturn = (Long) this.YugaRotation.get("saturn");
		this.YugaRotation.put("saturn", saturn + 4);

		Long Candrocca = (Long) this.YugaRotation.get("Candrocca");
		this.YugaRotation.put("Candrocca", Candrocca - 16);

		Long Rahu = (Long) this.YugaRotation.get("Rahu");
		this.YugaRotation.put("Rahu", Rahu - 12);

	}

	public void setSecondaryConstants() {
		// TODO: Add Tests if/when feasible
		myGlobals.YugaCivilDays = (Long) this.YugaRotation.get("star") - 
				(Long) this.YugaRotation.get("sun");
		myGlobals.YugaSynodicMonth = (Long) this.YugaRotation.get("moon") - 
				(Long) this.YugaRotation.get("sun");
		myGlobals.YugaAdhimasa = myGlobals.YugaSynodicMonth - 
				12 * (Long) this.YugaRotation.get("sun");
		myGlobals.YugaTithi = 30 * myGlobals.YugaSynodicMonth;
		myGlobals.YugaKsayadina = myGlobals.YugaTithi - myGlobals.YugaCivilDays;
	}

	public void setPlanetaryConstants() {
		// TODO: Add Tests if/when feasible
		// star
		this.PlanetRotation.put("star", 0);
		this.PlanetSighra.put("star", 0);
		this.PlanetApogee.put("star", 0);
		this.PlanetCircumm.put("star", 0);
		this.PlanetCircums.put("star", 0);

		// sun
		this.PlanetRotation.put("sun", (Long) this.YugaRotation.get("sun"));
		this.PlanetSighra.put("sun", (Long) this.YugaRotation.get("sun"));
		this.PlanetApogee.put("sun", 77 + 17 / 60);
		this.PlanetCircumm.put("sun", 13 + 50 / 60);
		this.PlanetCircums.put("sun", 0);

		// moon
		this.PlanetRotation.put("moon", this.YugaRotation.get("moon"));
		this.PlanetSighra.put("moon", 0);
		this.PlanetApogee.put("moon", 0);
		this.PlanetCircumm.put("moon", 31 + 50 / 60);
		this.PlanetCircums.put("moon", 0);

		// mercury
		this.PlanetRotation.put("mercury", this.YugaRotation.get("sun"));;
		this.PlanetSighra.put("mercury", this.YugaRotation.get("mercury"));
		this.PlanetApogee.put("mercury", 220 + 27 / 60);
		this.PlanetCircumm.put("mercury", 29);
		this.PlanetCircums.put("mercury", 131.5);

		// venus
		this.PlanetRotation.put("venus", this.YugaRotation.get("sun"));
		this.PlanetSighra.put("venus", this.YugaRotation.get("venus"));
		this.PlanetApogee.put("venus", 79 + 50 / 60);
		this.PlanetCircumm.put("venus", 11.5);
		this.PlanetCircums.put("venus", 261);

		// mars
		this.PlanetRotation.put("mars", this.YugaRotation.get("mars"));
		this.PlanetSighra.put("mars", this.YugaRotation.get("sun"));
		this.PlanetApogee.put("mars", 130 + 2 / 60);
		this.PlanetCircumm.put("mars", 73.5);
		this.PlanetCircums.put("mars", 233.5);

		// jupiter
		this.PlanetRotation.put("jupiter",this.YugaRotation.get("jupiter"));
		this.PlanetSighra.put("jupiter", this.YugaRotation.get("sun"));
		this.PlanetApogee.put("jupiter", 171 + 18 / 60);
		this.PlanetCircumm.put("jupiter", 32.5);
		this.PlanetCircums.put("jupiter", 71);

		// saturn
		this.PlanetRotation.put("saturn", this.YugaRotation.get("saturn"));
		this.PlanetSighra.put("saturn", this.YugaRotation.get("sun"));
		this.PlanetApogee.put("saturn", 236 + 37 / 60);
		this.PlanetCircumm.put("saturn", 48.5);
		this.PlanetCircums.put("saturn", 39.5);

		// Candrocca
		this.PlanetRotation.put("Candrocca", this.YugaRotation.get("Candrocca"));
		this.PlanetSighra.put("Candrocca", 0);
		this.PlanetApogee.put("Candrocca", 0);
		this.PlanetCircumm.put("Candrocca", 0);
		this.PlanetCircums.put("Candrocca", 0);

		// Rahu
		this.PlanetRotation.put("Rahu", this.YugaRotation.get("Rahu"));
		this.PlanetSighra.put("Rahu", 0);
		this.PlanetApogee.put("Rahu", 0);
		this.PlanetCircumm.put("Rahu", 0);
		this.PlanetCircums.put("Rahu", 0);
	} 
	public void setAyanamsa(Double ahargana){
		// TODO: Add Tests if/when feasible
		// Good reads:
		// https://en.wikipedia.org/wiki/Ayanamsa
		// http://pidaparthypanchangam.com/?m=201306&paged=2
		Double ayanamsa = ( 54 * 4320000 / myGlobals.YugaCivilDays / 3600 ) * ( ahargana - 1314930 );
		myGlobals.ayanadeg = kvmath.truncate(ayanamsa);
		myGlobals.ayanamin = 60 * kvmath.fractional(ayanamsa);
	}
	public Double getMeanLongitude (Double ahargana, Double rotation) {
		// TODO: Add Tests if/when feasible
		return 360 * kvmath.fractional(rotation * ahargana / myGlobals.YugaCivilDays);
	}
	public Double getTrueLongitude(Double ahargana, Double meanSolarLongitude, String planet) {
	    Double meanLong1, meanLong2, meanLong3;
	    Double argument;
	    Double anomaly1, anomaly2;
	    Double equation1, equation2, equation3, equation4, equation5;

	    // first sighra correction
	    if (planet == "mercury" || planet == "venus") {
	      anomaly1 = this.getMeanLongitude(ahargana, (Double) this.PlanetSighra.get(planet)) - meanSolarLongitude;
	    }
	    else {
	      anomaly1 = this.getMeanLongitude(ahargana, (Double) this.PlanetSighra.get(planet)) - (Double) this.PlanetMeanPosition.get(planet);
	    }
	    equation1 = this.getSighraEquation(anomaly1, planet);

	    // first manda correction
	    meanLong1 = (Double) this.PlanetMeanPosition.get(planet) + equation1 / 2;
	    argument = meanLong1 - (Double) this.PlanetApogee.get(planet);
	    equation2 = this.getMandaEquation(argument, planet);

	    // second manda correction
	    meanLong2 = meanLong1 - equation2 / 2;
	    argument = meanLong2 - (Double) this.PlanetApogee.get(planet);
	    equation3 = this.getMandaEquation(argument, planet);

	    // second sighra correction
	    meanLong3 = (Double) this.PlanetMeanPosition.get(planet) - equation3;
	    anomaly2 = this.getMeanLongitude(ahargana, (Double) this.PlanetSighra.get(planet)) - meanLong3;
	    equation4 = this.getSighraEquation(anomaly2, planet);

	    equation5 = 0.0;

	    // {$ifdef suryasiddhanta}
	    // {$else}
	    //    if (planet === 'mercury' || planet === 'venus') {
	    //        argument = meanSolarLongitude - (77 + 17 / 60);
	    //        equation5 = (13.5 / 360 * Math.sin(argument / math.radianInDegrees)) * math.radianInDegrees;
	    //    }
	    //    if (planet === 'venus') {
	    //        equation5 = equation5 - (1 + 7 / 60);
	    //    }
	    // {$endif}

	    return this.zero360(meanLong3 + equation4 + equation5);
	  }
	
	public Double getSighraEquation( Double anomaly, String planet) {
		    Double bhuja, koti, karna;
		    bhuja = (Double) this.PlanetCircums.get(planet) / 360 * Math.sin(anomaly / kvmath.radianMultiplier) * kvmath.radianMultiplier;
		    koti = (Double) this.PlanetCircums.get(planet) / 360 * Math.cos(anomaly / kvmath.radianMultiplier) * kvmath.radianMultiplier;
		    karna = Math.sqrt(kvmath.square(kvmath.radianMultiplier + koti) + kvmath.square(bhuja));

		    return Math.asin(bhuja / karna) * kvmath.radianMultiplier;
    }
	public Double declination (Double longitude) {
		// https://en.wikipedia.org/wiki/Declination
		return Math.asin(Math.sin(longitude / kvmath.radianMultiplier) * Math.sin(24 / kvmath.radianMultiplier)) *
				kvmath.radianMultiplier;
	}
	public Double getDaylightEquation (Double year, Double latitude) {
		// TODO: Add Tests if/when feasible
		Double meanSolarLongitude = this.getMeanLongitude(myGlobals.ahar, (Double) this.YugaRotation.get("sun"));

		// Sayana Solar Longitude and Declination
		Double sayanaMeanSolarLongitude = meanSolarLongitude + (54 / 3600) * (myGlobals.year - 499);
		Double sayanaDeclination = this.declination(sayanaMeanSolarLongitude); // See Sewell, p.10

		// Equation of day light by Analemma (https://en.wikipedia.org/wiki/Analemma)
		Double x = Math.tan(latitude / kvmath.radianMultiplier) * Math.tan(sayanaDeclination / kvmath.radianMultiplier);

		return 0.5 * Math.asin(x) / Math.PI;
	}
	public void setSunriseTime(Double eqTime) {
		// TODO: Add Tests if/when feasible
		Double sunriseTime = (0.25 - eqTime) * 24;
		myGlobals.sriseh = kvmath.truncate(sunriseTime);
		myGlobals.srisem = kvmath.truncate(60 * kvmath.fractional(sunriseTime));
	}
	public Double getMandaEquation (Double argument, String planet) {
		return Math.asin((Double) this.PlanetCircumm.get("planet") / 360 * Math.sin(argument / kvmath.radianMultiplier)) * kvmath.radianMultiplier;
	}

	public Double getTithi (Double tllong, Double tslong) {
		Double elong = tllong - tslong;
		elong = this.zero360(elong);
		return elong / 12;
	}
	public void setTithiSet(Double tithi) {
		// TODO: Add Tests if/when feasible
		myGlobals.tithi_day = kvmath.truncate(tithi) + 1;
		myGlobals.ftithi = kvmath.fractional(tithi);
	}
	public void  setSuklaKrsna() {
		// TODO: Add Tests if/when feasible
		if (15 < myGlobals.tithi_day) {
			myGlobals.tithi_day -= 15;
			myGlobals.paksa = "Krsnapaksa";
		} else {
			myGlobals.paksa = "Suklapaksa";
		}
		myGlobals.sukla_krsna = myGlobals.paksa;
	}
	public Double getTllong(Double ahar) {
		Double meanLunarLongitude = this.getMeanLongitude(ahar, (Double) this.YugaRotation.get("moon"));
		Double apogee = this.getMeanLongitude(ahar, (Double) this.YugaRotation.get("Candrocca") + 90);
		return this.zero360(meanLunarLongitude - this.getMandaEquation((meanLunarLongitude - apogee), "moon"));
	}
	public Double getTslong( Double ahar) {
		Double meanSolarLongitude = this.getMeanLongitude(ahar, (Double) this.YugaRotation.get("sun"));
		return this.zero360(meanSolarLongitude - this.getMandaEquation((meanSolarLongitude - (Double) this.PlanetApogee.get("sun")), "sun"));
	}
	public Double getElong(Double ahar) {
		Double elong = Math.abs(this.getTllong(ahar) - this.getTslong(ahar));
		if (180 < elong) {
			elong = 360 - elong;
		}
		return elong;
	}
	public Double findConj(Double leftX, Double leftY, Double rightX, Double rightY) {
		Double width = (rightX - leftX) / 2;
		Double centreX = (rightX + leftX) / 2;
		if (width < kvmath.epsilon) {
			return this.getElong(centreX);
		} else {
			Double centreY = this.getElong(centreX);
			Double relation = this.threeRelation(leftY, centreY, rightY);
			if (relation < 0) {
				rightX += width;
				rightY = this.getElong(rightX);
				return this.findConj(centreX, centreY, rightX, rightY);
			} else if (0 < relation) {
				leftX -= width;
				leftY = this.getElong(leftX);
				return this.findConj(leftX, leftY, centreX, centreY);
			} else {
				leftX += width / 2;
				leftY = this.getElong(leftX);
				rightX -= width / 2;
				rightY = this.getElong(rightX);
				return this.findConj(leftX, leftY, rightX, rightY);
			}
		}
	}
	public Double getConj(Double ahar) {
		return this.findConj(ahar - 2, this.getElong(ahar - 2), ahar + 2, this.getElong(ahar + 2));
	}
	public Double getClong(Double ahar, Double tithi) {
		Double newNew = myGlobals.YugaCivilDays / ((Double) this.YugaRotation.get("moon") - (Double) this.YugaRotation.get("sun"));
		ahar -= tithi * (newNew / 30);

		if (Math.abs(ahar - myGlobals.back_clong_ahar) < 1) {
			return (double) myGlobals.back_clong;
		} else if (Math.abs(ahar - myGlobals.back_nclong_ahar) < 1) {
			myGlobals.back_clong_ahar = myGlobals.back_nclong_ahar;
			myGlobals.back_clong = myGlobals.back_nclong;
			return (double) myGlobals.back_nclong;
		} else {
			myGlobals.back_clong_ahar = ahar;
			myGlobals.back_clong = this.getConj(ahar);
			return (double) myGlobals.back_clong;
		}
	}
	public double getNclong(Double ahar, Double tithi) {
		Double newNew = myGlobals.YugaCivilDays / ( (Double) this.YugaRotation.get("moon") - (Double) this.YugaRotation.get("sun"));
		ahar += (30 - tithi) * (newNew / 30);

		if (Math.abs(ahar - myGlobals.back_nclong_ahar) < 1) {
			return myGlobals.back_nclong;
		} else {
			myGlobals.back_nclong_ahar = ahar;
			myGlobals.back_nclong = this.getConj(ahar);
			return myGlobals.back_nclong;
		}
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}