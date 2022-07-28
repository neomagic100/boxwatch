import React from "react";
import CallerVolume from "./Graphs/CallerVolume/CallerVolume";
import DamagedPackages from "./Graphs/DamagedPackages/DamagedPackages";
import TimeSaved from "./Graphs/TimeSaved/TimeSaved";
import Returns from "./Graphs/Returns/Returns";

import "./KPI.css";

const KPI = (props) => {
  const title = props.title;
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
    case "Time Saved on Returns":
        graph = <TimeSaved className="time-saved__graph" />
  }

  return (
    <div className={className}>
      <div className="kpi__title">{title}</div>
      <div> {graph} </div>
    </div>
  );
};

export default KPI;