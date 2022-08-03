import React from "react";
import { useState } from "react";
import "./TimeSaved.css";
import camera from "../../../img/camera.png";
import clock from "../../../img/clock.png";

function TimeSaved() {
  const [timeSaved, setTimeSaved] = useState("9M");
  const [timeTaken, setTimeTaken] = useState(40);

  return (
    <div className="two_nums">
      <div className="num1">
        <img className="num1_image" src={clock} alt="clock"></img>
        <div className="time_saved_num">{timeSaved} hours</div>
        <div className="time_saved_subtitle">Total Time Saved This Month</div>
      </div>
      <div className="num2">
        <img className="num2_image" src={camera} alt="camera"></img>
        <div className="time_taken_num">{timeTaken} hours</div>
        <div className="time_taken_subtitle">
          Average Time For Detection This Month
        </div>
      </div>
    </div>
  );
}

export default TimeSaved;
