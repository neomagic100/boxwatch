import React from "react";
import TitleBar from "./components/TitleBar";
import KPI from "./components/KPI";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import "./App.css";

const theme = createTheme({
  palette: {
    primary: {
      main: "#ffc400"
    },
    secondary: {
      main: "#351C15"
    },
  }
})

function App() {
  return (
    <div className="app">
      <ThemeProvider theme={ theme }>
        <TitleBar />
        <div className="kpi-collection">
          <p className="kpi-collection__title__cs">Customer Service</p>
          <hr className="kpi-collection__goldbreak__cs"></hr>
          <KPI className="kpi__returns" title="Number of Returns From Endpoint" />
          <KPI className="kpi__caller" title="Caller Volume" />
          <KPI className="kpi__time" title="Time Saved on Returns" />
          <p className="kpi-collection__title__claims">Package Claims Management</p>
          <hr className="kpi-collection__goldbreak__claims"></hr>
          <p className="kpi-collection__title__transport">Transportation Cost Control</p>
          <hr className="kpi-collection__goldbreak__transport"></hr>
          <KPI className="kpi__claims" title="Damaged Package Claims" />
          <KPI className="kpi__transport" title="Cost of ICE vs NG vs EV" />
        </div>
      </ThemeProvider>
    </div>
  );
}

export default App;
