import React from "react";
import { Line } from "react-chartjs-2";
import { UserData } from "../../../data/ReturnData";
import { useState, useRef, useEffect } from "react";
import SpanToggleButton from "../../SpanToggleButton";
import "./Returns.css";
import { Chart as ChartJS } from "chart.js/auto";

const Returns = (props) => {
  const [percentColor, setPercentColor] = useState([]);
  const [userData, setUserData] = useState({
      labels: UserData.map((data) => data.date.substring(0,5)),
      datasets: [
        {
          label: "Returns",
          data: UserData.map((data) => data.returns),
          backgroundColor: [
            "#DDE8B9",
            "#E8D2AE",
            "#D7B29D",
            "#CB8589",
            "#796465",
          ],
        },
      ],
  });

  const [percent, setPercent] = useState([]);

  const inputRef1 = useRef();
  const inputRef2 = useRef();

  function filterDate(type) {
    const dates = UserData.map((data) => data.date);
    const volume = UserData.map((data) => data.returns);
    //slice the array
    let value1 = inputRef1.current.value;
    let value2 = inputRef2.current.value;

    const str1 = value1.toString();
    const str2 = value2.toString();
    var date1 = "";
    var date2 = "";
    var datesBack = 0;

    if (type === "3days") {
      //3 days back, inclusive so minus 2
      datesBack = 2;
    } else if (type === "1week") {
      //1 week back
      datesBack = 6;
    } else if (type === "1month") {
      datesBack = 29;
    }

    if (type === "basic") {
      date1 =
        str1.substring(5, 7) +
        "." +
        str1.substring(8, 10) +
        "." +
        str1.substring(0, 4);
      date2 =
        str2.substring(5, 7) +
        "." +
        str2.substring(8, 10) +
        "." +
        str2.substring(0, 4);
    } else if (type === "3days" || type === "1week" || type === "1month") {
      var today = new Date();
      var threeDays = new Date(
        new Date().setDate(new Date().getDate() - datesBack)
      );
      date1 =
        ("0" + (threeDays.getMonth() + 1)).slice(-2) +
        "." +
        ("0" + threeDays.getDate()).slice(-2) +
        "." +
        threeDays.getFullYear();
      date2 =
        ("0" + (today.getMonth() + 1)).slice(-2) +
        "." +
        ("0" + today.getDate()).slice(-2) +
        "." +
        today.getFullYear();
      }

    const indexstartdate = dates.indexOf(date1);
    const indexenddate = dates.indexOf(date2);
    //slice the array
    const filterDate = dates.slice(indexstartdate, indexenddate + 1);
    const filterDataPoints = volume.slice(indexstartdate, indexenddate + 1);

    const start = filterDataPoints[0];
    const end = filterDataPoints[filterDataPoints.length - 1];
    setPercent((end - start) / start);

    setUserData({
      labels: filterDate.map((data) => data.substring(0,5)),
      datasets: [
        {
          label: "Returns",
          data: filterDataPoints,
          backgroundColor: [
            "#DDE8B9",
            "#E8D2AE",
            "#D7B29D",
            "#CB8589",
            "#796465",
          ],
        },
      ],
    });
  }
  useEffect(() => {
    if (percent > 0) {
      setPercentColor({
        color: "red",
      });
    } else if (percent === 0) {
      setPercentColor({
        color: "grey",
      });
    } else {
      setPercentColor({
        color: "green",
      });
    }
  }, [percent]);
  return (
    <div className={ props.className }>
      <span className="returns__percent" style={percentColor}>
      {percent > 0 ? "+" : null}
      {(percent * 100).toFixed()}%</span>
      <Line data={userData} />
      <SpanToggleButton onClick={ (type) => filterDate(type) } />
      <div className="returns-filter">
        <p className="returns-filter__select"> Select a Timeframe: </p>
        <input className="returns-filter__date1" type="date" ref={inputRef1} />
        <p className="returns-filter__dateto">to</p>
        <input className="returns-filter__date2" onChange={() => filterDate("basic")} type="date" ref={inputRef2} />
      </div>
    </div>
  );
}

export default Returns;