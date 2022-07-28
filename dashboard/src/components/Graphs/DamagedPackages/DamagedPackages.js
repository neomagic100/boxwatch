import React from "react";
import { Bar } from "react-chartjs-2";
import { UserData } from "../../../data/DamagedPackagesData";
import { useState, useRef } from "react";
import SpanToggleButton from "../../SpanToggleButton";
import { Chart as ChartJS } from "chart.js/auto";
import { useSlotProps } from "@mui/base";
import "./DamagedPackages.css";

function DamagedPackages(props) {
  const [userData, setUserData] = useState({
    labels: UserData.map((data) => data.date),
    datasets: [
      {
        label: "Damages Detected",
        data: UserData.map((data) => data.returns),
        backgroundColor: ["#351C15"],
      },
      {
        label: "Damages Reported",
        data: UserData.map((data) => data.returns),
        backgroundColor: ["#ffc400"],
      },
    ],
  });
  // const length = userData.datasets[0].data.length;
  // const originalPercent =
  //   (userData.datasets[0].data[length] - userData.datasets[0].data[0]) /
  //   userData.datasets[0].data[0];
  // const [percent, setPercent] = useState(originalPercent);

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
    // console.log(date1);
    // console.log(date2);

    const indexstartdate = dates.indexOf(date1);
    const indexenddate = dates.indexOf(date2);
    // console.log(indexstartdate);
    // console.log(indexenddate);
    //slice the array
    const filterDate = dates.slice(indexstartdate, indexenddate + 1);
    const filterDataPoints = volume.slice(indexstartdate, indexenddate + 1);

    // console.log(filterDate, filterDataPoints);
    // console.log(filterDataPoints[0]);
    // console.log(filterDataPoints[filterDataPoints.length - 1]);

    setUserData({
      labels: filterDate,
      datasets: [
        {
          label: "Damages Detected",
          data: filterDataPoints,
          backgroundColor: ["#351C15"],
        },
        {
          label: "Damages Reported",
          data: filterDataPoints,
          backgroundColor: ["#ffc400"],
        },
      ],
    });
  }

  var options = {
    plugins: {
      title: {
        display: true,
        text: "",
      },
    },
    responsive: true,
    scales: {
      x: {
        stacked: true,
      },
      y: {
        stacked: true,
      },
    },
  };

  return (
    <div className={ props.className }>
      <Bar data={userData} options={options} />
      <SpanToggleButton onClick={ (type) => filterDate(type) }></SpanToggleButton>
      <div className="claims-filter">
        <p className="claims-filter__select"> Select a Timeframe: </p>
        <input className="claims-filter__date1" type="date" ref={inputRef1} />
        <p className="claims-filter__dateto">to</p>
        <input className="claims-filter__date2" onChange={() => filterDate("basic")} type="date" ref={inputRef2} />
      </div>
    </div>
  );
}

export default DamagedPackages;