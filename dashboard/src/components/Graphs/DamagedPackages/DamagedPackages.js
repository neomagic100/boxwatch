import React from "react";
import { Bar } from "react-chartjs-2";
import { useEffect, useState, useRef } from "react";
import SpanToggleButton from "../../SpanToggleButton";
import { Chart as ChartJS } from "chart.js/auto";
import { useSlotProps } from "@mui/base";
import "./DamagedPackages.css";

function DamagedPackages(props) {
  const [damageData, setDamageData] = useState([]);
  const fetchData = (url) => {
    fetch(
      "https://damagedpackages-dot-burner-keeburnh.ue.r.appspot.com/damagedPackages" +
        url,
      {
        method: "GET",
        header: {
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Credentials": true,
        },
      }
    )
      .then((response) => response.json())
      .then((data) => {
        setDamageData(data);
      });
  };
  useEffect(() => {
    fetchData("/dates/between/?start=2021-08-09&end=2022-08-08");
  }, []);

  var chartData = {
    labels: damageData.map((data) => data.date.substring(2,10)),
    datasets: [
      {
        label: "Damages Detected",
        data: damageData.map((data) => data.numDetected),
        backgroundColor: ["#351C15"],
      },
      {
        label: "Damages Reported",
        data: damageData.map((data) => data.numReported),
        backgroundColor: ["#ffc400"],
      },
    ],
  };

  var options = {
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
  const inputRef1 = useRef();
  const inputRef2 = useRef();

  function filterDate(type) {
    var url;
    if (type === "basic") {
      let value1 = inputRef1.current.value;
      let value2 = inputRef2.current.value;

      const str1 = value1.toString();
      const str2 = value2.toString();
      url = "/dates/between/?start=" + str1 + "&end=" + str2;
    } else if (
      type === "3days" ||
      type === "1week" ||
      type === "1month" ||
      type === "6month"
    ) {
      var datesBack = 0;
      if (type === "3days") {
        //3 days back, inclusive so minus 2
        datesBack = 2;
      } else if (type === "1week") {
        //1 week back
        datesBack = 6;
      } else if (type === "1month") {
        datesBack = 29;
      } else if (type === "6month") {
        datesBack = 179;
      }

      var today = new Date();
      var threeDays = new Date(
        new Date().setDate(new Date().getDate() - datesBack)
      );
      var date1 =
        threeDays.getFullYear() +
        "-" +
        ("0" + (threeDays.getMonth() + 1)).slice(-2) +
        "-" +
        ("0" + threeDays.getDate()).slice(-2);

      var date2 =
        today.getFullYear() +
        "-" +
        ("0" + (today.getMonth() + 1)).slice(-2) +
        "-" +
        ("0" + today.getDate()).slice(-2);
      url = "/dates/between/?start=" + date1 + "&end=" + date2;
    }

    fetchData(url);
  }

  return (
    <div className={ props.className }>
      <Bar data={ chartData } options={options} />
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