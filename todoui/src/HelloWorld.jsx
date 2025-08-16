
// This is a simple React component that displays a greeting message.import React from 'react';
import React from 'react';
//This component is a simple functional component that returns a JSX element/
// every time the name of the component should start with a capital letter
// and the component should be exported so that it can be used in other files
//We use export default to export the component so that it can be imported in other files
export default function HelloWorld() {
    const features = [
        "Welcome to the world of React!",
        "React is a JavaScript library for building user interfaces.",
        "It allows you to create reusable UI components.",
        "React is maintained by Facebook and a community of individual developers and companies.",
        "React is used to build single-page applications (SPAs).",
        "It allows you to create dynamic and interactive user interfaces.",
        "React uses a virtual DOM to improve performance.",
        "React is component-based, meaning you can build complex UIs by composing smaller components.",
        "React is declarative, meaning you can describe what the UI should look like based on the current state."
    ];

    return (
        <div className="max-w-3xl mx-auto p-6" style={{ textAlign: "left" }}>
            <h1 className="text-3xl font-bold mb-6">🚀 Getting Started with React</h1>
            <ul className="space-y-4">
                {features.map((feature, index) => (
                    <li
                        key={index}
                        className="p-4 rounded-lg shadow-lg bg-white border-l-4 border-blue-500 hover:shadow-xl transition"
                    >
                        {feature}
                    </li>
                ))}
            </ul>
        </div>
    );
}