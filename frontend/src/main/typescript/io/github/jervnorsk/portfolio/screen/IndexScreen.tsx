import React from "react";
import {ComponentProps} from "../util/ComponentUtil";
import Container from "../component/Container";
import Header from "../component/Header";
import Brand from "../component/Brand";
import Main from "../component/Main";
import Section from "../component/Section";

export default (props: ComponentProps) => {
    return (
        <Container>
            <Header>
                <Brand/>
            </Header>
            <Main>
                <Section from={Brand} />
            </Main>
        </Container>
    )
}
