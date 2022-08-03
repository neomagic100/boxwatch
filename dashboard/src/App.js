import React from "react";
import TitleBar from "./components/TitleBar";
import KPI from "./components/KPI";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import "./App.css";

const theme = createTheme({
  typography: {
  },
  palette: {
    primary: {
      main: "#ffc400"
    },
    secondary: {
      main: "#351C15"
    },
    light: {
      main: "#fce8a4"
    },
    lighter: {
      main: "#fcf4d9"
    }
  }
})

function App() {
  return (
    <div className="app">
      <ThemeProvider theme={ theme }>
        <TitleBar />
        <div className="kpi-collection">
          <p className="kpi-collection__cs"> Customer Service </p>
          <KPI className="kpi__caller" title="Caller Volume" />
          <KPI className="kpi__returns" title="Number of Returns From Endpoint" />
          <KPI className="kpi__time" title="" />
          <p className="kpi-collection__transport"> Transportation Cost Control </p>
          <KPI className="kpi__transport" title="Cost of ICE vs NG vs EV" />
          <KPI className="kpi__emissions" title="CO2 Reduction Goals" />
          <p className="kpi-collection__claims"> Package Claims Management </p>
          <KPI className="kpi__claims" title="Damaged Package Claims" />
        </div>
      </ThemeProvider>
    </div>
  );
}

export default App;
