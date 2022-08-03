import * as React from 'react';
import CircularProgress from '@mui/material/CircularProgress';
import "./ProgressCircle.css";

export default function CircularDeterminate(props) {
  const [progress, setProgress] = React.useState(0);

  React.useEffect(() => {
    const timer = setInterval(() => {
      setProgress((prevProgress) => (prevProgress >= 100 ? 0 : prevProgress + 1));
    }, 1000);

    return () => {
      clearInterval(timer);
    };
  }, []);

  return (
    <CircularProgress 
      className={ props.className } 
      variant="determinate" 
      value={ props.value } 
      thickness={2.5}
      color={ props.color }
    />
  );
}