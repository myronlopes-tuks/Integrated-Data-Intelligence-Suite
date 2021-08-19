import React from 'react';
import renderer from 'react-test-renderer';
import TimelineGraph from "../components/ContentSection/TimelineGraph";
import {BrowserRouter} from "react-router-dom";

it('renders correctly', () => {
    const tree = renderer
        .create(<BrowserRouter>
            <TimelineGraph/>
        </BrowserRouter>)
        .toJSON();
    expect(tree).toMatchSnapshot();
});