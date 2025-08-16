import React, { useState } from 'react';

export default function ButtonCout() {
    const [coiunt, setCount]= useState(0);
    const habdleClick = () => {
        setCount(coiunt + 1);
    }
    return(
        <div>
            <button onClick={habdleClick} className="btn btn-primary">
                Clicked {coiunt} times
            </button>
        </div>
    );

}