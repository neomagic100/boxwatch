import React from "react";
import { Line } from "react-chartjs-2";
import { ComparisonData } from "../../../data/ComparisonData";
import { useState } from "react";
// eslint-disable-next-line
import { Chart as ChartJS } from "chart.js/auto";
import "./TransportCost.css";

function TransportCost(props) {
  // eslint-disable-next-line
  const [userData, setUserData] = useState({
    labels: ComparisonData.reverse().map((data) => data.date.substring(0,3) + data.date.substring(data.date.length - 3, data.date.length)),
    datasets: [
      {
        label: "Gas Price",
        data: ComparisonData.map((data) => data.gas),
        backgroundColor: ["#a56124"],
        spanGaps: true,
        pointRadius: 2,
      },
      {
        label: "Natrual Gas Price",
        data: ComparisonData.map((data) => data.natural),
        backgroundColor: ["#ffc400"],
        spanGaps: true,
        pointRadius: 2,
      },
      {
        label: "Electric Price",
        data: ComparisonData.map((data) => data.electric),
        backgroundColor: ["#351c15"],
        spanGaps: true,
        pointRadius: 2,
      },
    ],
  });

  var options = {
    responsive: true,
    scales: {
      x: {
        ticks: {
          reverse: true,
        },
      },
    },
  };

  return (
    <div className={ props.className }>
      <Line data={userData} options={options} />
    </div>
  );
}

export default TransportCost;