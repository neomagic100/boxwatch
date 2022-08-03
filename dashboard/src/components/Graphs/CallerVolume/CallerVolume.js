import * as d3 from "d3";
import "./CallerVolume.css";
import { useState, useRef, useEffect } from "react";
import { FormControl, InputLabel, NativeSelect } from "@mui/material";

const drawSvg = ({ inputRef, url, width, height }) => {
  const container = d3.select(inputRef.current);

  var projection = d3
    .geoAlbersUsa()
    .translate([width / 2, height / 2]) // translate to center of screen
    .scale([1000]); // scale things down so see entire US

  // Define path generator
  var path = d3
    .geoPath() // path generator that will convert GeoJSON to SVG paths
    .projection(projection); // tell path generator to use albersUsa projection

  // Define linear scale for output
  const color = d3
    .scaleThreshold()
    .domain(d3.range(0, 10000, 10000 / 8))
    .range(d3.schemeYlOrBr[9]);

  color.domain().reverse();

  var svg = container.append("svg").attr("width", width).attr("height", height);

  // Append Div for tooltip to SVG
  var div = container
    .append("div")
    .attr("class", "tooltip")
    .style("opacity", 0);

  d3.json(url).then(function (data) {
    d3.json("us-states.json").then(function (json) {
      for (var i = 0; i < data.length; i++) {
        // Grab State Name
        var dataState = data[i].state;

        // Grab data value
        var dataValue = data[i].numberOfCalls;
        //console.log(dataValue);

        // Find the corresponding state inside the GeoJSON
        for (var j = 0; j < json.features.length; j++) {
          var jsonState = json.features[j].properties.abbrev;

          if (dataState === jsonState) {
            // Copy the data value into the JSON
            json.features[j].properties.volume = dataValue;

            // Stop looking through the JSON
            break;
          }
        }

        for(var k = 0; k < json.features.length; k++) {
          if(!json.features[k].properties.volume) {
            json.features[k].properties.volume = Math.floor(Math.random() * 1000);
          }
        }
      }
      svg
        .selectAll("path")
        .data(json.features)
        .enter()
        .append("path")
        .attr("d", path)
        .style("stroke", "#fff")
        .style("stroke-width", "1")
        .style("fill", function (d) {
          // Get data value
          var temp = d.properties.volume;
          var value = 0;
          // if (0 < temp && temp <= 1250) {
          //   value = 0;
          // } else if (1250 < temp && temp <= 2500) {
          //   value = 1;
          // } else if (2500 < temp && temp <= 3750) {
          //   value = 2;
          // } else if (3750 < temp && temp <= 5000) {
          //   value = 3;
          // }
          while (temp > color.domain()[value]) {
            value++;
          }
          // console.log(d.properties.name);
          // console.log(temp);
          // console.log(color.range());
          // console.log(color.range()[value]);
          if (value) {
            //If value exists…
            return color.range()[value];
          } else {
            //If value is undefined…
            return "rgb(213,222,217)";
          }
        })
        .on("mouseover", function (event, datum) {
          var [x, y] = d3.pointer(event);
          div.transition().duration(200).style("opacity", 0.9);
          div

            .text(
              `${datum.properties.name}:
             ${datum.properties.volume}`
            )

            .style("left", x + "px")
            .style("top", y + "px");
        })

        // fade out tooltip on mouse out
        .on("mouseout", function (d) {
          div.transition().duration(500).style("opacity", 0);
        });
    });
    var num = 10000;
    var index = 0;
    var legendText = [];
    while (num >= 0) {
      legendText[index] = num;
      index++;
      num = num - 1250;
      //console.log(index);
    }
    //console.log(legendText);
    var legend = container
      .append("svg")
      .attr("class", "legend")
      .attr("width", 140)
      .attr("height", 200)
      .selectAll("g")
      .data(color.domain().slice().reverse())
      .enter()
      .append("g")
      .attr("transform", function (d, i) {
        return "translate(0," + i * 20 + ")";
      });

    legend
      .append("rect")
      .attr("width", 18)
      .attr("height", 18)
      .style("fill", color);

    //console.log(legendText[8]);
    legend
      .append("text")
      .data(legendText)
      .attr("x", 24)
      .attr("y", 13)
      .attr("dy", "-0.15em")
      .text(function (text) {
        return text;
      });
  });
};

const CallerVolume = (props) => {
  const inputRef = useRef(null);
  const [hasRendered, setRender] = useState(false);
  const [url, setUrl] = useState("https://callervolume-dot-burner-keeburnh.ue.r.appspot.com/callerVolume/dates/quarter/?quarter=1");

  //drawSvg({ inputRef, ...props });
  // eslint-disable-next-line react-hooks/exhaustive-deps
  useEffect(() => {
    if (!hasRendered) {
      drawSvg({ inputRef, url, ...props });
      setRender(true);
    }
  });

  const changeUrl = (event) => {
    setUrl(event.target.value);
    setRender(false);

    d3.select("svg").remove();
    d3.select("svg").remove();
  };

  return(
    <div>
      <div className={props.className} id="graph-container" ref={inputRef}>
        <div className="quarter-select">
          <FormControl fullWidth>
            <InputLabel variant="standard" htmlFor="uncontrolled-native">
              Select Quarter
            </InputLabel>
            <NativeSelect
              defaultValue={1}
              inputProps={{
                name: "Quarter",
                id: "uncontrolled-native",
              }}
              onChange={changeUrl}
            >
              <option value={"https://callervolume-dot-burner-keeburnh.ue.r.appspot.com/callerVolume/dates/quarter/?quarter=1"}>Q1</option>
              <option value={"https://callervolume-dot-burner-keeburnh.ue.r.appspot.com/callerVolume/dates/quarter/?quarter=2"}>Q2</option>
              <option value={"https://callervolume-dot-burner-keeburnh.ue.r.appspot.com/callerVolume/dates/quarter/?quarter=3"}>Q3</option>
              <option value={"https://callervolume-dot-burner-keeburnh.ue.r.appspot.com/callerVolume/dates/quarter/?quarter=4"}>Q4</option>
            </NativeSelect>
          </FormControl>
        </div>
        
      </div>
    </div>
    
  );
};

export default CallerVolume;