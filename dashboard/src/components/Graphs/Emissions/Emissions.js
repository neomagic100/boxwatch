import { toHaveDisplayValue } from "@testing-library/jest-dom/dist/matchers";
import CircularDeterminate from "../../ProgressCircle";
import "./Emissions.css";

const Emissions = () => {
    let today = new Date();
    let month = today.toLocaleString('default', { month: 'short'});
    let day = today.getDate();

    return(
        <div className="emissions">
            <CircularDeterminate className="emissions-circle__outer" color="primary" value={ 75 } />
            <CircularDeterminate className="emissions-circle__mid" color="light" value={ 57 } />
            <CircularDeterminate className="emissions-circle__inner" color="lighter" value={ 100 }/>
            <p className="emissions-day">{ day }</p>
            <p className="emissions-month">{ month }</p>
            <ul className="emissions-year__reductions"><li><span className="emissions-year__reductions-weight">2500 LBS.</span><br /><span className="emissions-year__reductions-year">2022<br />Reductions</span></li></ul>
            <ul className="emissions-overall__reductions"><li><span className="emissions-overall__reductions-weight">50M Metric Tons</span><br /><span className="emissions-overall__reductions-overall">Overall<br /> Reductions</span></li></ul>
        </div>
    );
}

export default Emissions;