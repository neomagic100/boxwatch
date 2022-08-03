import React from "react";
import CallerVolume from "./Graphs/CallerVolume/CallerVolume";
import DamagedPackages from "./Graphs/DamagedPackages/DamagedPackages";
import TimeSaved from "./Graphs/TimeSaved/TimeSaved";
import Returns from "./Graphs/Returns/Returns";
import TransportCost from "./Graphs/TransportCost/TransportCost";
import Emissions from "./Graphs/Emissions/Emissions";

import "./KPI.css";
import { breadcrumbsClasses } from "@mui/material";

const KPI = (props) => {
  let title = props.title;
  const className = props.className;
  let graph = null;

  switch(title) {
    case "Number of Returns From Endpoint":
        graph = <Returns className="returns__graph" />;
        break;
    case "Caller Volume":
        graph = <CallerVolume className="caller-volume__graph" width={750} height={450} />;
        break;
    case "Damaged Package Claims":
        graph = <DamagedPackages className="damaged-packages__graph" />;
        break;
    case "Cost of ICE vs NG vs EV":
        graph = <TransportCost className="transport__graph" />;
        break;
    case "CO2 Reduction Goals":
        graph = <Emissions />;
        break;
    default:
        graph = <TimeSaved className="time-saved__graph" />
  }

  return (
    <div className={className}>
      {
        (title === "CO2 Reduction Goals") 
          ? <div className="kpi__title">CO<sub>2</sub> Reduction Goals</div> 
          : <div className="kpi__title">{title}</div>
      }
      <div> {graph} </div>
    </div>
  );
};

export default KPI;