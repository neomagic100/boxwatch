import React, { useState } from "react";
import { ToggleButton, ToggleButtonGroup } from "@mui/material";
import "./SpanToggleButton.css";

const SpanToggleButton = (props) => {
    const onClick = props.onClick;
    const [alignment, setAlignment] = useState('1month');

    const handleToggle = (event, newAlignment) => {
        if (newAlignment != null) {
            setAlignment(newAlignment);
            onClick(newAlignment);
        }
    }

    return(
        <ToggleButtonGroup
            className="returns-toggle"
            size="small"
            value={ alignment }
            onChange={ handleToggle }
            fullWidth
            exclusive
        >
            <ToggleButton 
                className="returns-toggle__button"
                value="3days" 
                disableRipple>3D</ToggleButton>
            <ToggleButton 
                className="returns-toggle__button"
                value="1week"
                disableRipple>1W</ToggleButton>
            <ToggleButton 
                className="returns-toggle__button"
                value="1month" 
                disableRipple>1M</ToggleButton>
            <ToggleButton
                className="returns-toggle__button"
                value="6months"
                disableRipple>6M</ToggleButton>
        </ToggleButtonGroup>
    );
}

export default SpanToggleButton;