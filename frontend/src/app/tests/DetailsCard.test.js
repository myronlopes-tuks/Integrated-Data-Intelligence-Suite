import React from 'react';
import renderer from 'react-test-renderer';
import DetailsCard from '../components/DetailsCard/DetailsCard' ;
import {unmountComponentAtNode} from "react-dom";

let container = null;
beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
  // cleanup on exiting
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});



it('DetailsCard renders correctly', () => {
    const tree = renderer
        .create(<DetailsCard/>)
        .toJSON();
    expect(tree).toMatchSnapshot();
});