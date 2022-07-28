import React from "react";
import UPSLogo from "../img/UPS-logo.png";
import PSLogo from "../img/publicissapient-logo.png";
import "./TitleBar.css";

const TitleBar = () => {
    return(
        <div className="title-bar">
            <div className="title-bar__inner">
                <img className="title-bar__inner__ups-logo" src={ UPSLogo }></img>
                <p className="title-bar__inner__title">KPI Dashboard</p>
                <p className="title-bar__inner__powered">Powered By</p>
                <img className="title-bar__inner__ps-logo" src={ PSLogo }></img>
            </div>
        </div>
    );
}

export default TitleBar;